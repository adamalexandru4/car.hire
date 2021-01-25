package ro.agilehub.javacourse.car.hire.user.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.test.context.ActiveProfiles;
import ro.agilehb.javacourse.car.hire.api.exceptions.BadRequestException;
import ro.agilehb.javacourse.car.hire.api.exceptions.NotFoundException;
import ro.agilehub.javacourse.car.hire.api.model.ResourceCreatedDTO;
import ro.agilehub.javacourse.car.hire.api.model.StatusEnum;
import ro.agilehub.javacourse.car.hire.user.domain.UserDO;
import ro.agilehub.javacourse.car.hire.user.entity.Country;
import ro.agilehub.javacourse.car.hire.user.entity.User;
import ro.agilehub.javacourse.car.hire.user.mappers.UserMapper;
import ro.agilehub.javacourse.car.hire.user.repository.CountryRepository;
import ro.agilehub.javacourse.car.hire.user.repository.UserRepository;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static com.mongodb.internal.connection.tlschannel.util.Util.assertTrue;
import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
@ActiveProfiles("test")
public class UserServiceImplTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private CountryRepository countryRepository;

    @Mock
    private UserMapper userMapper;

    @InjectMocks
    private UserServiceImpl userService;

    private static final String mockObjectId = "507f191e810c19729de860ea";

    @Test
    public void findAllUsers_whenEmpty_returnEmptyList() {
        List<User> userList = Collections.emptyList();

        when(userRepository.findAll()).thenReturn(userList);

        assertTrue(userService.getAllUsersWithStatus(null).isEmpty());
    }

    @Test
    public void findAllUsers_whenUsers_returnResult() {
        User user = mock(User.class);

        when(userRepository.findAll()).thenReturn(Collections.singletonList(user));

        var result = userService.getAllUsersWithStatus(null);
        assertEquals(1, result.size());
    }

    @Test
    public void findAllUser_withStatus_returnResult() {

        User user = mock(User.class);
        UserDO userDO = new UserDO();
        userDO.setStatus(StatusEnum.ACTIVE);

        when(userMapper.mapToDO(eq(user))).thenReturn(userDO);
        when(userRepository.findAllByStatus(any())).thenReturn(Collections.singletonList(user));

        assertEquals(1, userService.getAllUsersWithStatus(StatusEnum.ACTIVE).size());
        assertEquals(StatusEnum.ACTIVE, userService.getAllUsersWithStatus(StatusEnum.ACTIVE).get(0).getStatus());
    }

    @Test
    public void findAllUser_withStatus_returnEmpty() {
        when(userRepository.findAllByStatus(any())).thenReturn(Collections.emptyList());

        var result = userService.getAllUsersWithStatus(StatusEnum.ACTIVE);
        assertTrue(result.isEmpty());
    }

    @Test
    public void createUser_withWrongCountry_throwBadRequestException() {
        UserDO user = mock(UserDO.class);

        assertThrows(BadRequestException.class, () -> userService.createNewUser(user));
    }

    @Test
    public void createUser_withSuccess() {

        when(countryRepository.findById(any())).thenReturn(Optional.of(mock(Country.class)));
        when(userMapper.mapUserToResourceCreated(any())).thenReturn(mock(ResourceCreatedDTO.class));

        UserDO user = mock(UserDO.class);
        assertNotNull(userService.createNewUser(user));
    }

    @Test
    public void deleteUser_withWrongFormatId_throwIllegalArgumentException(){
        assertThrows(IllegalArgumentException.class, () -> userService.deleteUser("test"));
    }

    @Test
    public void deleteUser_withWrongId_throwNotFoundException() {
        assertThrows(NotFoundException.class, () -> userService.deleteUser(mockObjectId));
    }

    @Test
    public void deleteUser_withSuccess() {
        when(userRepository.findById(any())).thenReturn(Optional.of(mock(User.class)));

        assertDoesNotThrow(() -> userService.deleteUser(mockObjectId));
    }

    @Test
    public void getUser_withWrongId_throwIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> userService.getUser("test"));
    }

    @Test
    public void getUser_withWrongId_throwNotFoundException() {
        assertThrows(NotFoundException.class, () -> userService.getUser(mockObjectId));
    }

    @Test
    public void getUser_withSuccess() {
        when(userMapper.mapToDO(any(User.class))).thenReturn(mock(UserDO.class));
        when(userRepository.findById(any())).thenReturn(Optional.of(mock(User.class)));

        assertNotNull(userService.getUser(mockObjectId));
    }
}
