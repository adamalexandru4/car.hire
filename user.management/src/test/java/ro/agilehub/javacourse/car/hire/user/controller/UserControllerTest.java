package ro.agilehub.javacourse.car.hire.user.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONArray;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import ro.agilehub.javacourse.car.hire.api.model.*;
import ro.agilehub.javacourse.car.hire.user.MockMvcSetup;
import ro.agilehub.javacourse.car.hire.user.domain.UserDO;
import ro.agilehub.javacourse.car.hire.user.mappers.UserMapper;
import ro.agilehub.javacourse.car.hire.user.service.UserService;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
@WebMvcTest(UserController.class)
@ActiveProfiles("integrationtest")
public class UserControllerTest extends MockMvcSetup {

    @MockBean
    private UserService userService;

    @MockBean
    private UserMapper userMapper;

    @Test
    @WithMockUser("alexandru")
    public void whenGetAllUsers_returnListUserDTO() throws Exception {

        List<UserDO> users = new ArrayList<>();
        users.add(mock(UserDO.class));

        when(userService.getAllUsersWithStatus(any())).thenReturn(users);
        when(userService.mapUserCountry(any())).thenReturn(mock(UserDO.class));
        when(userMapper.mapToDTO(any(UserDO.class))).thenReturn(mock(UserDTO.class));

        var getResult = mockMvc.perform(MockMvcRequestBuilders.get("/user"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        JSONArray jsonArray = new JSONArray(getResult.getResponse().getContentAsString());

        assertThat(jsonArray.length()).isEqualTo(1);
    }

    @Test
    @WithMockUser("alexandru")
    public void whenDeleteUser_returnOk() throws Exception {

        var getResult = mockMvc.perform(MockMvcRequestBuilders.delete("/user/123"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        ResponseDTO responseDTO = objectMapper.readValue(getResult.getResponse().getContentAsString(), ResponseDTO.class);
        assertNotNull(responseDTO);
    }

    @Test
    @WithMockUser("alexandru")
    public void whenUserExistsForID_returnValidDTO() throws Exception {

        UserDTO userDTO = new UserDTO();
        userDTO.setUsername("alexandru");

        when(userService.getUser(any())).thenReturn(mock(UserDO.class));
        when(userService.mapUserCountry(any())).thenReturn(mock(UserDO.class));
        when(userMapper.mapToDTO(any(UserDO.class))).thenReturn(userDTO);

        var getResult = mockMvc.perform(MockMvcRequestBuilders.get("/user/123"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        UserDTO responseUserDTO = objectMapper.readValue(getResult.getResponse().getContentAsString(), UserDTO.class);
        assertEquals(userDTO.getUsername(), responseUserDTO.getUsername());

    }

    @Test
    @WithMockUser("alexandru")
    public void whenNewUser_returnResourceCreated() throws Exception {

        when(userMapper.mapToDO(any(NewUserDTO.class))).thenReturn(mock(UserDO.class));
        when(userService.createNewUser(any(UserDO.class))).thenReturn(mock(UserDO.class));


        ResourceCreatedDTO resourceCreatedDTO = new ResourceCreatedDTO();
        resourceCreatedDTO.setId("123");
        when(userMapper.mapUserToResourceCreated(any(UserDO.class))).thenReturn(resourceCreatedDTO);

        NewUserDTO newUserDTO = new NewUserDTO();
        newUserDTO.setUsername("alexandru");

        var getResult = mockMvc.perform(
                MockMvcRequestBuilders.post("/user")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(newUserDTO))
        ).andExpect(MockMvcResultMatchers.status().isCreated())
                .andReturn();

        ResourceCreatedDTO response = objectMapper.readValue(getResult.getResponse().getContentAsString(), ResourceCreatedDTO.class);
        assertEquals(resourceCreatedDTO.getId(), response.getId());
    }

    @Test
    @WithMockUser("alexandru")
    public void whenUpdateUserWithPatchError_returnInternalError() throws Exception {

        PatchDocument patchDocument = new PatchDocument();
        patchDocument.setOp(OpEnum.REPLACE);
        patchDocument.setPath("/wrong/path");
        patchDocument.setValue("value");
        List<PatchDocument> patchDocuments = Collections.singletonList(patchDocument);

        mockMvc.perform(MockMvcRequestBuilders.patch("/user/123")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(new ObjectMapper().writeValueAsString(patchDocuments)))
                .andExpect(MockMvcResultMatchers.status().isInternalServerError());
    }

    @Test
    @WithMockUser("alexandru")
    public void whenUpdateUserCorrectly_returnOk() throws Exception {

        UserDO userDO = new UserDO();
        userDO.setUsername("valueBefore");

        when(userService.getUser(any())).thenReturn(userDO);

        PatchDocument patchDocument = new PatchDocument();
        patchDocument.setOp(OpEnum.REPLACE);
        patchDocument.setPath("/username");
        patchDocument.setValue("valueAfter");
        List<PatchDocument> patchDocuments = Collections.singletonList(patchDocument);

        mockMvc.perform(MockMvcRequestBuilders.patch("/user/123")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(patchDocuments)))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }
}
