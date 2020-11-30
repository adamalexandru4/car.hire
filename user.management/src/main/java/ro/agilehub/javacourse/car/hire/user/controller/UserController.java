package ro.agilehub.javacourse.car.hire.user.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import ro.agilehub.javacourse.car.hire.api.model.*;
import ro.agilehub.javacourse.car.hire.api.specification.UserApi;
import ro.agilehub.javacourse.car.hire.user.service.UserService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class UserController implements UserApi {

    private final UserService userService;

    @Override
    public ResponseEntity<ResourceCreatedDTO> createUser(@Valid NewUserDTO user) {
        ResourceCreatedDTO resourceCreatedDTO = userService.createNewUser(user);
        return ResponseEntity.ok(resourceCreatedDTO);
    }

    @Override
    public ResponseEntity<ResponseDTO> deleteUser(String id) {
        return ResponseEntity.ok(userService.deleteUser(id));
    }

    @Override
    public ResponseEntity<List<UserDTO>> getAllUsers(@Valid String status) {
        return ResponseEntity.ok(userService.getAllUsersWithStatus(status));
    }

    @Override
    public ResponseEntity<UserDTO> getUserByID(String id) {
        return ResponseEntity.ok(userService.getUser(id));
    }

    @Override
    public ResponseEntity<ResponseDTO> updateUserDetails(String id, @Valid List<PatchDocument> patchDocument) {
        return ResponseEntity.ok(userService.updateUser(id, patchDocument));
    }
}
