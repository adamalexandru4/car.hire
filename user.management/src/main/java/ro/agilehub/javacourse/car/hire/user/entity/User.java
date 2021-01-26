package ro.agilehub.javacourse.car.hire.user.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.*;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import ro.agilehub.javacourse.car.hire.api.model.StatusEnum;

import java.time.*;

@Data
@EqualsAndHashCode(of = "id")
@Document("users")
@JsonIgnoreProperties(ignoreUnknown = true)
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
    private StatusEnum      status;

    private ObjectId        country;

    @CreatedBy
    private String          createdBy;
    @CreatedDate
    private LocalDateTime createdAt;
    @LastModifiedBy
    private String          lastModifiedBy;
    @LastModifiedDate
    private LocalDateTime  lastModifiedAt;
}
