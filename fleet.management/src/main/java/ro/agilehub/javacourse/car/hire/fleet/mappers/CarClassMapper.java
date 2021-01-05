package ro.agilehub.javacourse.car.hire.fleet.mappers;

import org.mapstruct.Mapper;
import ro.agilehub.javacourse.car.hire.api.model.CarClassEnum;

@Mapper(componentModel = "spring")
public interface CarClassMapper {

     default CarClassEnum toEnum(String value) {
        if (value == null) {
            return null;
        }
        return CarClassEnum.valueOf(value);
    }

    default String toEnum(CarClassEnum enumValue) {
        if (enumValue == null) {
            return null;
        }
        return enumValue.getValue();
    }
}
