package ro.agilehub.javacourse.car.hire.user.repository;

import org.bson.types.ObjectId;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import ro.agilehub.javacourse.car.hire.api.model.StatusEnum;
import ro.agilehub.javacourse.car.hire.user.entity.Country;
import ro.agilehub.javacourse.car.hire.user.entity.User;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@DataMongoTest
@ActiveProfiles("test")
public class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CountryRepository countryRepository;

    public static ObjectId countryID;
    public static User user;

    @Before
    public void setup() {
        // Country setup
        Country country = new Country();
        country.setName("Romania");
        country = countryRepository.save(country);
        countryID = country.getId();

        // User setup
        User mockUser = new User();
        mockUser.setCountry(countryID);
        mockUser.setFirstname("Alexandru");
        mockUser.setUserStatus(StatusEnum.ACTIVE);

        user = userRepository.save(mockUser);
    }

    @After
    public void cleanup() {
        countryRepository.deleteAll();
        userRepository.deleteAll();
    }

    @Test
    public void findUserWithStatusActive_returnUser() {
        var userFound = userRepository.getAllByUserStatus(StatusEnum.ACTIVE);

        assertFalse(userFound.isEmpty());
        assertEquals(userFound.get(0).getUserStatus(), StatusEnum.ACTIVE);
        assertEquals(userFound.get(0).getFirstname(), user.getFirstname());
    }

    @Test
    public void findUserWithStatusDeleted_returnEmpty() {
        var userFound = userRepository.getAllByUserStatus(StatusEnum.DELETED);

        assertTrue(userFound.isEmpty());
    }
}
