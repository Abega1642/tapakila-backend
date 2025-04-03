package dev.razafindratelo.tapakilaBackend.exception;

public class NotImplementedException extends RuntimeException {
    public NotImplementedException(String message) {
        super(String.format("Not implemented exception : %s", message));
    }
}
