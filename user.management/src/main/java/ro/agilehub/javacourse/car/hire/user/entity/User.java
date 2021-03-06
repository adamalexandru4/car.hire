package ro.agilehub.javacourse.car.hire.user.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.bson.types.ObjectId;
import org.joda.time.DateTime;
import org.springframework.data.annotation.*;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import ro.agilehub.javacourse.car.hire.api.model.StatusEnum;

@Data
@EqualsAndHashCode(of = "id")
@Document("users")
public class User {
    @Id
    @Field(name = "_id")
    private ObjectId        id;

    private String          username;
    private String          firstname;
    private String          lastname;
    private String          password;
    private String          driverLicense;
    private String          title;
    private StatusEnum      userStatus;

    private ObjectId        country;

    @CreatedBy
    private String          createdBy;
    @CreatedDate
    private DateTime        createdAt;
    @LastModifiedBy
    private String          lastModifiedBy;
    @LastModifiedDate
    private DateTime        lastModifiedAt;
}
