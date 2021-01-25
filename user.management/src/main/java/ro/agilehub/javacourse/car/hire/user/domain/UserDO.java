package ro.agilehub.javacourse.car.hire.user.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import ro.agilehub.javacourse.car.hire.api.model.StatusEnum;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserDO {
    private String      id;
    private String      username;
    private String      firstname;
    private String      lastname;
    private String      password;
    private String      driverLicense;
    private String      title;
    private StatusEnum  status;

    private String      countryID;
    private CountryDO   countryDO;
}
