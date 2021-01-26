package ro.agilehub.javacourse.car.hire.user.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Data
@EqualsAndHashCode(of = "id")
@Document("countries")
@JsonIgnoreProperties(ignoreUnknown = true)
public class Country {

    @Id
    @Field("_id")
    private ObjectId id;

    private String name;
    private String isoCode;

}
