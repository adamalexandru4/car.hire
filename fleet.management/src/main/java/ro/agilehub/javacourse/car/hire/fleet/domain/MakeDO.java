package ro.agilehub.javacourse.car.hire.fleet.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MakeDO {
    private String id;
    private String name;
    private String description;
}
