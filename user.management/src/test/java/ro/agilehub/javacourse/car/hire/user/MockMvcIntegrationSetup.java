package ro.agilehub.javacourse.car.hire.user;

import org.junit.After;
import org.junit.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;

public abstract class MockMvcIntegrationSetup extends MockMvcSetup {

    @Autowired
    protected MongoTemplate mongoTemplate;

    @Before
    public void setup() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext)
                .apply(springSecurity())
                .build();
    }

    @After
    public void cleanup() {
        for (String collection : getDroppedCollections()) {
            mongoTemplate.dropCollection(collection);
        }
    }

    protected abstract String[] getDroppedCollections();
}
