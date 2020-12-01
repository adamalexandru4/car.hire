package ro.agilehub.javacourse.car.hire.fleet.enums;

import ro.agilehub.javacourse.car.hire.user.enums.UserStatusEnum;

import java.util.Optional;

public enum CarStatusEnum {
    ACTIVE("Active"),
    DELETED("Deleted");

    private final String value;

    CarStatusEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }

    public static Optional<CarStatusEnum> ofValue(String value) {
        for (CarStatusEnum status: CarStatusEnum.values()) {
            if(status.getValue().equals(value))
                return Optional.of(status);
        }

        return Optional.empty();
    }

}
