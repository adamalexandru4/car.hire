package ro.agilehub.javacourse.car.hire.user.service;

import ro.agilehub.javacourse.car.hire.api.model.*;
import ro.agilehub.javacourse.car.hire.user.entity.User;

import java.util.List;

public interface UserService {

    User createNewUser(User newUser);

    void deleteUser(String id);

    User getUser(String id);

    List<User> getAllUsersWithStatus(StatusEnum status);

    void updateUser(User user);

    UserDTO mapUserCountry(User user);

}
