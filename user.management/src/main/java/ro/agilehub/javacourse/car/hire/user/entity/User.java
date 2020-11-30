package ro.agilehub.javacourse.car.hire.user.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Data
@Builder
@EqualsAndHashCode(of = "id")
@Document("users")
public class User {
    @Id
    @Field(name = "_id")
    private String          id;

    private String          username;
    private String          firstname;
    private String          lastname;
    private String          password;
    private String          driverLicense;
    private String          title;
    private String          userStatus;

    @DBRef(db = "countries")
    private Country         country;

}
