package ro.agilehub.javacourse.car.hire.user.service;

import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Service;
import ro.agilehb.javacourse.car.hire.api.exceptions.BadRequestException;
import ro.agilehb.javacourse.car.hire.api.exceptions.NotFoundException;
import ro.agilehub.javacourse.car.hire.api.model.*;
import ro.agilehub.javacourse.car.hire.user.entity.Country;
import ro.agilehub.javacourse.car.hire.user.entity.User;
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
    public ResourceCreatedDTO createNewUser(User newUser) {

        Country country = countryRepository.findById(newUser.getCountry())
                .orElseThrow(() -> new BadRequestException("Country of residence not found"));

        userRepository.save(newUser);
        return userMapper.mapUserToResourceCreated(newUser);
    }

    @Override
    public ResponseDTO deleteUser(String id) {
        User user = userRepository.findById(new ObjectId(id))
            .orElseThrow(() -> new NotFoundException("User not found"));

        userRepository.delete(user);

        ResponseDTO response = new ResponseDTO();
        response.setMessage("User deleted successfully");
        return response;
    }

    @Override
    public UserDTO getUser(String id) {
        return userRepository.findById(new ObjectId(id))
                .map(this::mapUserCountry)
                .orElseThrow(() -> new NotFoundException("User not found"));
    }

    @Override
    public List<UserDTO> getAllUsersWithStatus(StatusEnum status) {
        List<User> users = null;

        if (status != null) {
            users = userRepository.getAllByUserStatus(status);
        } else {
            users = userRepository.findAll();
        }

        return users.stream()
                .map(this::mapUserCountry)
                .collect(Collectors.toList());
    }

    @Override
    public ResponseDTO updateUser(String id, List<PatchDocument> patchDocument) {

        User user = userRepository.findById(new ObjectId(id))
            .orElseThrow(() -> new NotFoundException("User not found"));

        // TODO: patch logic

        return null;
    }


    private UserDTO mapUserCountry(User user) {
        Country country = countryRepository
                .findById(user.getCountry())
                .orElse(null);

        return userMapper.userToUserDTO(user, country);
    }
}
