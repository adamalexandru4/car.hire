package ro.agilehub.javacourse.car.hire.user.enums;

import java.util.Optional;

public enum UserStatusEnum {
    ACTIVE(Constants.ACTIVE_VALUE),
    DELETED(Constants.DELETED_VALUE);

    public static class Constants {
        public static final String ACTIVE_VALUE = "Active";
        public static final String DELETED_VALUE = "Deleted";
    }

    private final String value;

    UserStatusEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }

    public static Optional<UserStatusEnum> ofValue(String value) {
        for (UserStatusEnum status: UserStatusEnum.values()) {
            if(status.getValue().equals(value))
                return Optional.of(status);
        }

        return Optional.empty();
    }

}
