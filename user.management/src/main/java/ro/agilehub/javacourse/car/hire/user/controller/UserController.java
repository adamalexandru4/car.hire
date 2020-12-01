package ro.agilehub.javacourse.car.hire.user.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import ro.agilehb.javacourse.car.hire.api.controller.ExceptionController;
import ro.agilehub.javacourse.car.hire.api.model.*;
import ro.agilehub.javacourse.car.hire.api.specification.UserApi;
import ro.agilehub.javacourse.car.hire.user.entity.User;
import ro.agilehub.javacourse.car.hire.user.mappers.UserMapper;
import ro.agilehub.javacourse.car.hire.user.service.UserService;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class UserController extends ExceptionController implements UserApi {

    private final UserService userService;
    private final UserMapper userMapper;

    @Override
    public ResponseEntity<ResourceCreatedDTO> createUser(@Valid NewUserDTO newUser) {

        User user = userMapper.mapDTOToEntity(newUser);

        ResourceCreatedDTO resourceCreatedDTO = userService.createNewUser(user);
        return ResponseEntity.ok(resourceCreatedDTO);
    }

    @Override
    public ResponseEntity<ResponseDTO> deleteUser(String id) {
        return ResponseEntity.ok(userService.deleteUser(id));
    }

    @Override
    public ResponseEntity<List<UserDTO>> getAllUsers(@Valid StatusEnum status) {
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
