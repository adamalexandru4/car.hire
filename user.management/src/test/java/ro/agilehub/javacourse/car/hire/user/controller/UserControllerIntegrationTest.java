package ro.agilehub.javacourse.car.hire.user.controller;

import org.bson.types.ObjectId;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import ro.agilehub.javacourse.car.hire.api.model.ResourceCreatedDTO;
import ro.agilehub.javacourse.car.hire.api.model.UserDTO;
import ro.agilehub.javacourse.car.hire.user.MockMvcIntegrationSetup;
import ro.agilehub.javacourse.car.hire.user.entity.Country;
import ro.agilehub.javacourse.car.hire.user.repository.CountryRepository;

import static org.junit.Assert.assertEquals;

@SpringBootTest
@AutoConfigureMockMvc
@RunWith(SpringRunner.class)
@ActiveProfiles("integrationtest")
public class UserControllerIntegrationTest extends MockMvcIntegrationSetup {

    @Autowired
    protected CountryRepository countryRepository;

    protected static final String countryOfResidence = "Romania";
    protected static ObjectId countryId;

    @Before
    public void init() {
        Country country = new Country();
        country.setIsoCode("ISO-1234");
        country.setName(countryOfResidence);
        countryId = countryRepository.save(country).getId();
    }

    @Test
    @WithMockUser("alexandru")
    public void whenAddUser_findById() throws Exception{
        UserDTO userDTO = new UserDTO();
        userDTO.setCountryOfResidence(String.valueOf(countryId));
        userDTO.setFirstname("Alexandru");
        userDTO.setUsername("adamalexandru");

        var postResult = mockMvc.perform(MockMvcRequestBuilders.post("/user")
            .contentType("application/json")
            .content(objectMapper.writeValueAsString(userDTO)))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andReturn();

        var createdDTO = objectMapper.readValue(postResult.getResponse().getContentAsString(), ResourceCreatedDTO.class);

        var getResult = mockMvc.perform(MockMvcRequestBuilders.get("/user/" + createdDTO.getId()))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        var getUserDTO = objectMapper.readValue(getResult.getResponse().getContentAsString(), UserDTO.class);

        assertEquals("Alexandru", getUserDTO.getFirstname());
    }

    @Override
    protected String[] getDroppedCollections() {
        return new String[] { "users" };
    }
}
