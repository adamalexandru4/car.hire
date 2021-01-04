package ro.agilehub.javacourse.car.hire.user.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONArray;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import ro.agilehub.javacourse.car.hire.api.model.ResponseDTO;
import ro.agilehub.javacourse.car.hire.api.model.UserDTO;
import ro.agilehub.javacourse.car.hire.user.mappers.UserMapper;
import ro.agilehub.javacourse.car.hire.user.service.UserService;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@WebMvcTest(UserController.class)
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void whenGetAllUsers_returnListUserDTO() throws Exception {

        List<UserDTO> users = new ArrayList<>();
        users.add(mock(UserDTO.class));

        when(userService.getAllUsersWithStatus(any())).thenReturn(users);

        var getResult = mockMvc.perform(MockMvcRequestBuilders.get("/user"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        JSONArray jsonArray = new JSONArray(getResult.getResponse().getContentAsString());

        assertThat(jsonArray.length()).isEqualTo(1);
    }

    @Test
    public void whenDeleteUser_returnOk() throws Exception {

        when(userService.deleteUser(any())).thenReturn(mock(ResponseDTO.class));

        var getResult = mockMvc.perform(MockMvcRequestBuilders.delete("/user/123"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        ResponseDTO responseDTO = objectMapper.readValue(getResult.getResponse().getContentAsString(), ResponseDTO.class);
        assertNotNull(responseDTO);
    }



}
