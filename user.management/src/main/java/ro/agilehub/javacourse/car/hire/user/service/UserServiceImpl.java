package ro.agilehub.javacourse.car.hire.user.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Service;
import ro.agilehb.javacourse.car.hire.api.exceptions.BadRequestException;
import ro.agilehb.javacourse.car.hire.api.exceptions.NotFoundException;
import ro.agilehub.javacourse.car.hire.api.model.*;
import ro.agilehub.javacourse.car.hire.user.domain.UserDO;
import ro.agilehub.javacourse.car.hire.user.entity.Country;
import ro.agilehub.javacourse.car.hire.user.entity.User;
import ro.agilehub.javacourse.car.hire.user.mappers.CountryMapper;
import ro.agilehub.javacourse.car.hire.user.mappers.UserMapper;
import ro.agilehub.javacourse.car.hire.user.repository.CountryRepository;
import ro.agilehub.javacourse.car.hire.user.repository.UserRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final CountryRepository countryRepository;

    private final UserMapper userMapper;
    private final CountryMapper countryMapper;

    @Override
    public UserDO createNewUser(UserDO newUser) {

        checkForAddingNewUser(newUser);

        Country country = countryRepository.findById(new ObjectId(newUser.getCountryID()))
                .orElseThrow(() -> new BadRequestException("Country of residence not found"));
        newUser.setCountryDO(countryMapper.mapToDO(country));

        User newUserEntity = userMapper.mapToEntity(newUser);

        return userMapper.mapToDO(userRepository.save(newUserEntity));
    }

    private void checkForAddingNewUser(UserDO userDO) {

        if (userDO.getId() != null) {
            throw new BadRequestException("User should not have ID already set");
        }

        if (userRepository.findByUsername(userDO.getUsername()).isPresent()) {
            throw new BadRequestException("Username exists already");
        }

    }

    @Override
    public void deleteUser(String id) {
        UserDO user = getUser(id);

        userRepository.deleteById(new ObjectId(user.getId()));
        log.info("User: " + id + " deleted successfully");
    }

    @Override
    public UserDO getUser(String id) {
        var user = userRepository.findById(new ObjectId(id))
                .orElseThrow(() -> new NotFoundException("User not found"));

        return userMapper.mapToDO(user);
    }

    @Override
    public List<UserDO> getAllUsersWithStatus(StatusEnum status) {
        List<UserDO> users = null;

        if (status != null) {
            users = userMapper.mapToDOList(userRepository.findAllByStatus(status));
        } else {
            users = userMapper.mapToDOList(userRepository.findAll());
        }

        return users;
    }

    @Override
    public void updateUser(UserDO user) {
        userRepository.save(userMapper.mapToEntity(user));
        log.info("User: " + user.getId() + " updated successfully");
    }


    public UserDO mapUserCountry(UserDO user) {
        Country country = countryRepository
                .findById(new ObjectId(user.getCountryID()))
                .orElse(null);

        user.setCountryDO(countryMapper.mapToDO(country));

        return user;
    }
}
