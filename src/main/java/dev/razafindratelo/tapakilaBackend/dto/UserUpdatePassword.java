package dev.razafindratelo.tapakilaBackend.dto;

public record UserUpdatePassword (String userEmail, String newPassword) {
}
