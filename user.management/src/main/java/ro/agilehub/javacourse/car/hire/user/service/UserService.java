package ro.agilehub.javacourse.car.hire.user.service;

import ro.agilehub.javacourse.car.hire.api.model.*;

import java.util.List;

public interface UserService {

    ResourceCreatedDTO createNewUser(NewUserDTO newUser);

    ResponseDTO deleteUser(String id);

    UserDTO getUser(String id);

    List<UserDTO> getAllUsersWithStatus(String status);

    ResponseDTO updateUser(String id, List<PatchDocument> patchDocument);

}
