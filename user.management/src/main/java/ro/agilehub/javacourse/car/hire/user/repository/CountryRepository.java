package ro.agilehub.javacourse.car.hire.user.repository;

import ro.agilehub.javacourse.car.hire.user.entity.Country;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CountryRepository extends MongoRepository<Country, String> {

    Optional<Country> findByName(String name);
}
