package ro.agilehub.javacourse.car.hire.user.service;

import ro.agilehub.javacourse.car.hire.api.model.*;
import ro.agilehub.javacourse.car.hire.user.domain.UserDO;
import ro.agilehub.javacourse.car.hire.user.entity.User;

import java.util.List;

public interface UserService {

    UserDO createNewUser(UserDO newUser);

    void deleteUser(String id);

    UserDO getUser(String id);

    List<UserDO> getAllUsersWithStatus(StatusEnum status);

    void updateUser(UserDO user);

    UserDO mapUserCountry(UserDO user);

}
