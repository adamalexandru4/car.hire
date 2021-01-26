package ro.agilehub.javacourse.car.hire.user.repository;

import ro.agilehub.javacourse.car.hire.user.entity.Country;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CountryRepository extends MongoRepository<Country, ObjectId> {
}
