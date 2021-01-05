package ro.agilehub.javacourse.car.hire.fleet.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.bson.types.ObjectId;
import org.joda.time.DateTime;
import org.springframework.data.annotation.*;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import ro.agilehub.javacourse.car.hire.api.model.CarClassEnum;
import ro.agilehub.javacourse.car.hire.api.model.StatusEnum;

@Data
@EqualsAndHashCode(of = "id")
@Document("cars")
public class Car {

    @Id
    @Field("_id")
    private ObjectId        id;

    private ObjectId        make;
    private String          model;
    private int             year;
    private int             mileage;
    private float           fuel;
    private CarClassEnum    carClass;
    private StatusEnum      status;

    @CreatedBy
    private String          createdBy;
    @CreatedDate
    private DateTime createdAt;
    @LastModifiedBy
    private String          lastModifiedBy;
    @LastModifiedDate
    private DateTime        lastModifiedAt;
}
