package ro.agilehub.javacourse.car.hire.user.mappers;

import org.mapstruct.Mapper;
import ro.agilehub.javacourse.car.hire.api.model.StatusEnum;

@Mapper(componentModel = "spring")
public interface StatusMapper {

    default StatusEnum toEnum(String value) {
        if (value == null) {
            return null;
        }
        return StatusEnum.valueOf(value);
    }

    default String toEnum(StatusEnum enumValue) {
        if (enumValue == null) {
            return null;
        }
        return enumValue.getValue();
    }
}
