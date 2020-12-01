package ro.agilehub.javacourse.car.hire.fleet.mappers;

import org.mapstruct.Mapper;
import ro.agilehub.javacourse.car.hire.fleet.enums.CarClassEnum;
import ro.agilehub.javacourse.car.hire.fleet.enums.CarStatusEnum;

@Mapper(componentModel = "spring")
public interface CarStatusMapper {

    default CarStatusEnum toEnum(String value) {
        if (value == null) {
            return null;
        }
        return CarStatusEnum.valueOf(value);
    }

    default String toEnum(CarStatusEnum enumValue) {
        if (enumValue == null) {
            return null;
        }
        return enumValue.getValue();
    }
}
