package ro.agilehub.javacourse.car.hire.user.service;

import lombok.RequiredArgsConstructor;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Service;
import ro.agilehb.javacourse.car.hire.api.exceptions.BadRequestException;
import ro.agilehb.javacourse.car.hire.api.exceptions.NotFoundException;
import ro.agilehub.javacourse.car.hire.api.model.*;
import ro.agilehub.javacourse.car.hire.user.entity.Country;
import ro.agilehub.javacourse.car.hire.user.entity.User;
import ro.agilehub.javacourse.car.hire.user.enums.UserStatusEnum;
import ro.agilehub.javacourse.car.hire.user.mappers.UserMapper;
import ro.agilehub.javacourse.car.hire.user.repository.CountryRepository;
import ro.agilehub.javacourse.car.hire.user.repository.UserRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final CountryRepository countryRepository;
    private final UserMapper userMapper = Mappers.getMapper(UserMapper.class);

    public ResourceCreatedDTO createNewUser(NewUserDTO newUser) {

        Country country = countryRepository.findByName(newUser.getCountryOfResidence())
                .orElseThrow(() -> new BadRequestException("Country of residence not found"));

        User user = userRepository.save(User.builder()
                .firstname(newUser.getFirstname())
                .lastname(newUser.getLastname())
                .password(newUser.getPassword())
                .userStatus(UserStatusEnum.ACTIVE.getValue())
                .driverLicense(newUser.getDriverLicense())
                .country(country)
                .build());

        return userMapper.mapUserToResourceCreated(user);
    }

    public ResponseDTO deleteUser(String id) {
        User user = userRepository.findById(id)
            .orElseThrow(() -> new NotFoundException("User not found"));

        userRepository.delete(user);

        ResponseDTO response = new ResponseDTO();
        response.setMessage("User deleted successfully");
        return response;
    }

    public UserDTO getUser(String id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("User not found"));

        return userMapper.userToUserDTO(user);
    }

    public List<UserDTO> getAllUsersWithStatus(String status) {
        List<User> users = null;

        if (status != null && !status.isEmpty()) {
            UserStatusEnum userStatus = UserStatusEnum.ofValue(status)
                    .orElseThrow(() -> new BadRequestException("Status value not valid"));
            users = userRepository.getAllByUserStatus(userStatus.getValue());
        } else {
            users = userRepository.findAll();
        }

        return users.stream()
                .map(userMapper::userToUserDTO)
                .collect(Collectors.toList());
    }

    public ResponseDTO updateUser(String id, List<PatchDocument> patchDocument) {

        User user = userRepository.findById(id)
            .orElseThrow(() -> new NotFoundException("User not found"));

        // TODO: patch logic

        ResponseDTO response = new ResponseDTO();
        return response;
    }
}
