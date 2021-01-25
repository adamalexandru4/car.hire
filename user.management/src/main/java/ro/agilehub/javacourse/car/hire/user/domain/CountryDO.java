package ro.agilehub.javacourse.car.hire.user.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CountryDO {
    private String      id;
    private String      name;
    private String      isoCode;
}
