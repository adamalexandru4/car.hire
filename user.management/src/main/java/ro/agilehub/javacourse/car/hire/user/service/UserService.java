package ro.agilehub.javacourse.car.hire.user.service;

import ro.agilehub.javacourse.car.hire.api.model.*;
import ro.agilehub.javacourse.car.hire.user.entity.User;

import java.util.List;

public interface UserService {

    ResourceCreatedDTO createNewUser(User newUser);

    ResponseDTO deleteUser(String id);

    UserDTO getUser(String id);

    List<UserDTO> getAllUsersWithStatus(StatusEnum status);

    ResponseDTO updateUser(String id, List<PatchDocument> patchDocument);

}
