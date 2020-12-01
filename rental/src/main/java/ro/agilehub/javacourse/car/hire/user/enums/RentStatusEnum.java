package ro.agilehub.javacourse.car.hire.user.enums;

import java.util.Optional;

public enum RentStatusEnum {
    ACTIVE("Active"),
    CANCELED("Canceled");

    private String value;

    RentStatusEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }

    public static Optional<RentStatusEnum> ofValue(String value) {
        for (RentStatusEnum status: RentStatusEnum.values()) {
            if(status.getValue().equals(value))
                return Optional.of(status);
        }

        return Optional.empty();
    }
}
