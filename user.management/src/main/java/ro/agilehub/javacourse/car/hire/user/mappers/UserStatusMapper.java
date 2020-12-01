package ro.agilehub.javacourse.car.hire.user.mappers;

import org.mapstruct.Mapper;
import ro.agilehub.javacourse.car.hire.user.enums.UserStatusEnum;

@Mapper(componentModel = "spring")
public interface UserStatusMapper {

    default UserStatusEnum toEnum(String value) {
        if (value == null) {
            return null;
        }
        return UserStatusEnum.valueOf(value);
    }

    default String toEnum(UserStatusEnum enumValue) {
        if (enumValue == null) {
            return null;
        }
        return enumValue.getValue();
    }
}
