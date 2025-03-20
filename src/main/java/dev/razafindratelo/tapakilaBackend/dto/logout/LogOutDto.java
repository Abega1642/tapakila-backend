package dev.razafindratelo.tapakilaBackend.dto.logout;

public record LogOutDto (String userEmail, LogOutStatus logOutStatus, String logOutMessage) {
}
