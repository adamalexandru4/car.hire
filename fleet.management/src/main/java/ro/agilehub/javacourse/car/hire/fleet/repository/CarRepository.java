package ro.agilehub.javacourse.car.hire.fleet.repository;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import ro.agilehub.javacourse.car.hire.fleet.entity.Car;

import java.util.List;

@Repository
public interface CarRepository extends MongoRepository<Car, ObjectId> {

    List<Car> getAllByStatus(String value);
}
