package dev.razafindratelo.tapakilaBackend.exception;

public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException(String message) {
        super(String.format("Resource not found exception : %s", message));
    }
}
