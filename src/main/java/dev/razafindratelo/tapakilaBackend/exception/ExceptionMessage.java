package dev.razafindratelo.tapakilaBackend.exception;

import org.springframework.http.HttpStatus;

import java.util.Objects;

public record ExceptionMessage(String message, int code, HttpStatus status) {
    @Override
    public boolean equals(Object o) {
        if (!(o instanceof ExceptionMessage(String message1, int code1, HttpStatus status1)))
            return false;
        return code() == code1
                && Objects.equals(message(), message1) && status() == status1;
    }

    @Override
    public int hashCode() {
        return Objects.hash(message(), code(), status());
    }
}
