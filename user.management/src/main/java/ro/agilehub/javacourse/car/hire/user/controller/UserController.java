package ro.agilehub.javacourse.car.hire.user.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.fge.jsonpatch.JsonPatch;
import com.github.fge.jsonpatch.JsonPatchException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RestController;
import ro.agilehb.javacourse.car.hire.api.controller.ExceptionController;
import ro.agilehub.javacourse.car.hire.api.model.*;
import ro.agilehub.javacourse.car.hire.api.specification.UserApi;
import ro.agilehub.javacourse.car.hire.user.domain.UserDO;
import ro.agilehub.javacourse.car.hire.user.mappers.UserMapper;
import ro.agilehub.javacourse.car.hire.user.service.UserService;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@PreAuthorize("hasAuthority('MANAGER')")
@Slf4j
@RequiredArgsConstructor
public class UserController extends ExceptionController implements UserApi {

    private final UserService userService;
    private final UserMapper userMapper;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public ResponseEntity<ResourceCreatedDTO> createUser(@Valid NewUserDTO newUser) {
        UserDO createdUser = userService.createNewUser(userMapper.mapToDO(newUser));
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
        List<UserDO> userList = userService.getAllUsersWithStatus(status);

        List<UserDTO> userDTO = userList.stream().map(userDO -> userMapper.mapToDTO(userService.mapUserCountry(userDO)))
                .collect(Collectors.toList());

        return ResponseEntity.ok(userDTO);
    }

    @Override
    public ResponseEntity<UserDTO> getUserByID(String id) {
        UserDO user = userService.getUser(id);
        UserDTO userDTO = userMapper.mapToDTO(userService.mapUserCountry(user));

        return ResponseEntity.ok(userDTO);
    }

    @Override
    public ResponseEntity<ResponseDTO> updateUserDetails(String id, @Valid List<PatchDocument> patchDocument) {
        ResponseDTO response = new ResponseDTO();

        try {
            JsonPatch jsonPatch = objectMapper.convertValue(patchDocument, JsonPatch.class);

            // Get reservation from DB
            UserDO reservation = userService.getUser(id);
            JsonNode reservationPatch = jsonPatch.apply(objectMapper.convertValue(reservation, JsonNode.class));
            UserDO finalReservation = objectMapper.treeToValue(reservationPatch, UserDO.class);

            userService.updateUser(finalReservation);

            response.setMessage("Reservation updated successfully");
            return ResponseEntity.ok(response);
        } catch (JsonProcessingException | JsonPatchException e) {
            log.error(e.getMessage());

            response.setMessage("JSON patch exception");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }
}
