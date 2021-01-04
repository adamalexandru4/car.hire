package ro.agilehub.javacourse.car.hire.user.repository;

import org.bson.types.ObjectId;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.junit4.SpringRunner;
import ro.agilehub.javacourse.car.hire.user.entity.Country;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@DataMongoTest
public class CountryRepositoryTest {

    @Autowired
    private CountryRepository countryRepository;

    private static ObjectId countryID;

    @Before
    public void setup() {
        Country country = new Country();

        country.setIsoCode("123");
        country.setName("Romania");

        country = countryRepository.save(country);
        countryID = country.getId();
    }

    @After
    public void cleanup() {
        countryRepository.deleteAll();
    }

    @Test
    public void findById_withMatch_returnObject() {
        var country = countryRepository.findById(countryID);

        assertTrue(country.isPresent());
        assertEquals(country.get().getId(), countryID);
    }


    @Test
    public void findById_noMatch_returnEmpty() {
        var country = countryRepository.findById(new ObjectId());

        assertFalse(country.isPresent());
    }
}
