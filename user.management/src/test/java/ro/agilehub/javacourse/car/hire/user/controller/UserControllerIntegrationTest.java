package ro.agilehub.javacourse.car.hire.user.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.bson.types.ObjectId;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.mongo.embedded.EmbeddedMongoAutoConfiguration;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import ro.agilehub.javacourse.car.hire.api.model.ResourceCreatedDTO;
import ro.agilehub.javacourse.car.hire.api.model.UserDTO;
import ro.agilehub.javacourse.car.hire.user.entity.Country;
import ro.agilehub.javacourse.car.hire.user.repository.CountryRepository;

import static org.junit.Assert.assertEquals;

@SpringBootTest
@AutoConfigureMockMvc
@RunWith(SpringRunner.class)
@EnableAutoConfiguration(exclude = {EmbeddedMongoAutoConfiguration.class})
public class UserControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private CountryRepository countryRepository;

    private static final String countryOfResidence = "Romania";
    private static ObjectId countryId;

    @Autowired
    private ObjectMapper objectMapper;

    @Before
    public void setup() {
        Country country = new Country();
        country.setIsoCode("ISO-1234");
        country.setName(countryOfResidence);
        countryId = countryRepository.save(country).getId();
    }

    @Test
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
