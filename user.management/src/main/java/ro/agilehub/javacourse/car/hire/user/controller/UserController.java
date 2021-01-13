package ro.agilehub.javacourse.car.hire.user.controller;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.fge.jsonpatch.JsonPatch;
import com.github.fge.jsonpatch.JsonPatchException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RestController;
import ro.agilehb.javacourse.car.hire.api.controller.ExceptionController;
import ro.agilehub.javacourse.car.hire.api.model.*;
import ro.agilehub.javacourse.car.hire.api.specification.UserApi;
import ro.agilehub.javacourse.car.hire.user.entity.User;
import ro.agilehub.javacourse.car.hire.user.mappers.UserMapper;
import ro.agilehub.javacourse.car.hire.user.service.UserService;

import javax.json.JsonStructure;
import javax.json.JsonValue;
import javax.validation.Valid;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@PreAuthorize("hasAuthority('MANAGER')")
@RequiredArgsConstructor
public class UserController extends ExceptionController implements UserApi {

    private final UserService userService;
    private final UserMapper userMapper;

    @Override
    public ResponseEntity<ResourceCreatedDTO> createUser(@Valid NewUserDTO newUser) {
        User createdUser = userService.createNewUser(userMapper.mapDTOToEntity(newUser));
        ResourceCreatedDTO resourceCreatedDTO = userMapper.mapUserToResourceCreated(createdUser);
        return new ResponseEntity<>(resourceCreatedDTO, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<ResponseDTO> deleteUser(String id) {
        userService.deleteUser(id);

        ResponseDTO response = new ResponseDTO();
        response.setMessage("User deleted successfully");

        return ResponseEntity.ok(response);
    }

    @Override
    public ResponseEntity<List<UserDTO>> getAllUsers(@Valid StatusEnum status) {
        List<User> userList = userService.getAllUsersWithStatus(status);

        List<UserDTO> userDTO = userList.stream().map(userService::mapUserCountry).collect(Collectors.toList());

        return ResponseEntity.ok(userDTO);
    }

    @Override
    public ResponseEntity<UserDTO> getUserByID(String id) {
        User user = userService.getUser(id);
        UserDTO userDTO = userService.mapUserCountry(user);

        return ResponseEntity.ok(userDTO);
    }

    @Override
    public ResponseEntity<ResponseDTO> updateUserDetails(String id, @Valid List<PatchDocument> patchDocument) {
        try {
            User user = userService.getUser(id);
            User userPatched = applyPatchToUser(patchDocument, user);
            userService.updateUser(userPatched);
        } catch (JsonPatchException | IOException exception) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
        return null;
    }

    User applyPatchToUser(List<PatchDocument> patchDocument, User user) throws JsonPatchException, IOException {
        /* NOT WORKING FOR NESTED OBJECTS */
//        ObjectMapper mapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
//        JsonPatch patchUser = mapper.convertValue(patchDocument, JsonPatch.class);
//        JsonNode patched = patchUser.apply(mapper.convertValue(user, JsonNode.class));
//        return mapper.treeToValue(patched, User.class);
        return null;
    }
}
