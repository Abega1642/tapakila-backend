package dev.razafindratelo.tapakilaBackend.entity.enums;

import com.fasterxml.jackson.annotation.JsonCreator;

public enum UserRole {
    ADMIN,
    USER;

    @JsonCreator
    public static UserRole fromString(String value) {
        for (UserRole userRole : UserRole.values()) {
            if (userRole.name().equalsIgnoreCase(value)) {
                return userRole;
            }
        }
        return null;
    }
}
