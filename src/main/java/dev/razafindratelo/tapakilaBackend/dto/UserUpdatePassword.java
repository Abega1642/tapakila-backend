package dev.razafindratelo.tapakilaBackend.dto;

public record UserUpdatePassword (String validationCode, String userEmail, String newPassword) {
}
