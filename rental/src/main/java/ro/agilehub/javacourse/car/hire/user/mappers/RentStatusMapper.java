package ro.agilehub.javacourse.car.hire.user.mappers;

import org.mapstruct.Mapper;
import ro.agilehub.javacourse.car.hire.user.enums.RentStatusEnum;

@Mapper(componentModel = "spring")
public interface RentStatusMapper {

    default RentStatusEnum toEnum(String value) {
        if (value == null) {
            return null;
        }
        return RentStatusEnum.valueOf(value);
    }

    default String toEnum(RentStatusEnum enumValue) {
        if (enumValue == null) {
            return null;
        }
        return enumValue.getValue();
    }
}
