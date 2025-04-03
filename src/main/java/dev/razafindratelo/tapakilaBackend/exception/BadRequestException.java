package dev.razafindratelo.tapakilaBackend.exception;

public class BadRequestException extends RuntimeException {
    public BadRequestException(String message) {
        super(String.format("Bad request exception : %s", message));
    }
}
