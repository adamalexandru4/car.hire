package ro.agilehub.javacourse.car.hire.user.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import ro.agilehub.javacourse.car.hire.api.model.StatusEnum;

import java.time.OffsetDateTime;


@Data
@EqualsAndHashCode(of = "id")
@Document("reservations")
public class Reservation {

    @Id
    @Field("_id")
    private ObjectId id;

    private ObjectId userId;
    private ObjectId carId;

    private OffsetDateTime startDateTime;
    private OffsetDateTime endDateTime;
    private StatusEnum status;

}
