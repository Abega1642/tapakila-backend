package dev.razafindratelo.tapakilaBackend.dto.logout;

import lombok.Getter;

@Getter
public enum LogOutStatus {
    SUCCESS("The user is logged out with success"),
    ERROR("The user could not be logged out");

    private final String message;
    LogOutStatus(String message) {
        this.message = message;
    }
}
