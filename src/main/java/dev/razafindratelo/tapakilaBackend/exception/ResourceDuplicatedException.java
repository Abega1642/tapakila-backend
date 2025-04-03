package dev.razafindratelo.tapakilaBackend.exception;

public class ResourceDuplicatedException extends RuntimeException {
    public ResourceDuplicatedException(String message) {
        super(String.format("Resource duplicated exception : %s", message));
    }
}
