package ro.agilehub.javacourse.car.hire.fleet.repository;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;
import ro.agilehub.javacourse.car.hire.fleet.entity.Make;

import java.util.Optional;

@Repository
public interface MakeRepository extends MongoRepository<Make, String> {

    Optional<Make> findByName(String name);
}
