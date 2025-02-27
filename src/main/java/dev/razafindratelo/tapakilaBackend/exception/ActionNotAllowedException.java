package dev.razafindratelo.tapakilaBackend.exception;

public class ActionNotAllowedException extends RuntimeException {
    public ActionNotAllowedException(String message) {
        super(String.format("Action not allowed exception : %s", message));
    }
}
