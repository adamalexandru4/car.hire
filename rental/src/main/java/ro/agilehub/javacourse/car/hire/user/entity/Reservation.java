package ro.agilehub.javacourse.car.hire.user.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import ro.agilehub.javacourse.car.hire.fleet.entity.Car;
import ro.agilehub.javacourse.car.hire.user.enums.RentStatusEnum;

import java.time.LocalDateTime;


@Data
@EqualsAndHashCode(of = "_id")
@Document("reservations")
public class Reservation {

    @Id
    @Field("_id")
    private String id;

    @DBRef(db = "users")
    private User userId;

    @DBRef(db = "cars")
    private Car carId;

    private LocalDateTime startDateTime;
    private LocalDateTime endDateTime;
    private RentStatusEnum status;

}
