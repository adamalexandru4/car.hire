package ro.agilehub.javacourse.car.hire.user.repository;

import ro.agilehub.javacourse.car.hire.user.entity.Reservation;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RentalRepository extends MongoRepository<Reservation, ObjectId> {
    List<Reservation> getAllByStatus(String value);
}
