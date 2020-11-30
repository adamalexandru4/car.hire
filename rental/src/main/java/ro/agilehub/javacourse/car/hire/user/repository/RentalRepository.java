package ro.agilehub.javacourse.car.hire.user.repository;

import ro.agilehub.javacourse.car.hire.user.entity.Reservation;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RentalRepository extends MongoRepository<Reservation, String> {
}
