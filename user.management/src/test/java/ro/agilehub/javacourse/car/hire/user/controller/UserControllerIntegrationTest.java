package ro.agilehub.javacourse.car.hire.user.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.bson.types.ObjectId;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.mongo.embedded.EmbeddedMongoAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.event.annotation.BeforeTestClass;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import ro.agilehub.javacourse.car.hire.api.model.ResourceCreatedDTO;
import ro.agilehub.javacourse.car.hire.api.model.UserDTO;
import ro.agilehub.javacourse.car.hire.user.MockMvcSetup;
import ro.agilehub.javacourse.car.hire.user.entity.Country;
import ro.agilehub.javacourse.car.hire.user.repository.CountryRepository;

import static org.junit.Assert.assertEquals;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.*;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;

@SpringBootTest
@AutoConfigureMockMvc
@RunWith(SpringRunner.class)
@EnableAutoConfiguration(exclude = {EmbeddedMongoAutoConfiguration.class})
@ActiveProfiles("integrationtest")
public class UserControllerIntegrationTest extends MockMvcSetup {

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

        assertEquals(getUserDTO.getFirstname(), "Alexandru");
    }

}
