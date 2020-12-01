package ro.agilehub.javacourse.car.hire.user.service;

import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
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
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final CountryRepository countryRepository;
    private final UserMapper userMapper;

    @Override
    public ResourceCreatedDTO createNewUser(NewUserDTO newUser) {

        Country country = countryRepository.findById(new ObjectId(newUser.getCountryOfResidence()))
                .orElseThrow(() -> new BadRequestException("Country of residence not found"));

        User user = userMapper.mapDTOToEntity(newUser, country);
        userRepository.save(user);

        return userMapper.mapUserToResourceCreated(user);
    }

    @Override
    public ResponseDTO deleteUser(String id) {
        User user = userRepository.findById(id)
            .orElseThrow(() -> new NotFoundException("User not found"));

        userRepository.delete(user);

        ResponseDTO response = new ResponseDTO();
        response.setMessage("User deleted successfully");
        return response;
    }

    @Override
    public UserDTO getUser(String id) {
        return userRepository.findById(id)
                .map(this::mapUserCountry)
                .orElseThrow(() -> new NotFoundException("User not found"));
    }

    @Override
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
                .map(this::mapUserCountry)
                .collect(Collectors.toList());
    }

    @Override
    public ResponseDTO updateUser(String id, List<PatchDocument> patchDocument) {

        User user = userRepository.findById(id)
            .orElseThrow(() -> new NotFoundException("User not found"));

        // TODO: patch logic

        ResponseDTO response = new ResponseDTO();
        return response;
    }


    private UserDTO mapUserCountry(User user) {
        Country country = countryRepository
                .findById(user.getCountry())
                .orElse(null);

        return userMapper.userToUserDTO(user, country);
    }
}
