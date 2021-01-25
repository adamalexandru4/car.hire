package ro.agilehub.javacourse.car.hire.fleet.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ro.agilehub.javacourse.car.hire.api.model.CarClassEnum;
import ro.agilehub.javacourse.car.hire.api.model.StatusEnum;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CarDO {
    private String id;

    private String makeID;
    private MakeDO makeDO;

    private String model;
    private int year;
    private int mileage;
    private float fuel;
    private CarClassEnum carClass;
    private StatusEnum status;
}
