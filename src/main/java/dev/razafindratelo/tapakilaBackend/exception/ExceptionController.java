package dev.razafindratelo.tapakilaBackend.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionController {

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ExceptionMessage> runtimeExceptionHandler(RuntimeException e) {
        ExceptionMessage em = new ExceptionMessage(
                e.getMessage(),
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                HttpStatus.INTERNAL_SERVER_ERROR
        );
        return new ResponseEntity<>(em, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(NotImplementedException.class)
    public ResponseEntity<ExceptionMessage> handleNotImplementedException(NotImplementedException notImplemented) {
        ExceptionMessage em = new ExceptionMessage(
                notImplemented.getMessage(),
                HttpStatus.NOT_IMPLEMENTED.value(),
                HttpStatus.NOT_IMPLEMENTED
        );
        return new ResponseEntity<>(em, HttpStatus.NOT_IMPLEMENTED);
    }

    @ExceptionHandler(ActionNotAllowedException.class)
    public ResponseEntity<ExceptionMessage> handleNotAllowedException(ActionNotAllowedException notAllowed) {
        ExceptionMessage em = new ExceptionMessage(
                notAllowed.getMessage(),
                HttpStatus.FORBIDDEN.value(),
                HttpStatus.FORBIDDEN
        );
        return new ResponseEntity<>(em, HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(ResourceDuplicatedException.class)
    public ResponseEntity<ExceptionMessage> handleResourceDuplicatedException(ResourceDuplicatedException duplicated) {
        ExceptionMessage em = new ExceptionMessage(
                duplicated.getMessage(),
                HttpStatus.CONFLICT.value(),
                HttpStatus.CONFLICT
        );
        return new ResponseEntity<>(em, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ExceptionMessage> handleResourceNotFoundException(ResourceNotFoundException notFound) {
        ExceptionMessage em = new ExceptionMessage(
                notFound.getMessage(),
                HttpStatus.NOT_FOUND.value(),
                HttpStatus.NOT_FOUND
        );
        return new ResponseEntity<>(em, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<ExceptionMessage> handleBadRequestException(BadRequestException badRequest) {
        ExceptionMessage em = new ExceptionMessage(
                badRequest.getMessage(),
                HttpStatus.BAD_REQUEST.value(),
                HttpStatus.BAD_REQUEST
        );
        return new ResponseEntity<>(em, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ExceptionMessage> handleIllegalArgumentException(IllegalArgumentException illegal) {
        ExceptionMessage em = new ExceptionMessage(
                illegal.getMessage(),
                HttpStatus.BAD_REQUEST.value(),
                HttpStatus.BAD_REQUEST
        );
        return new ResponseEntity<>(em, HttpStatus.BAD_REQUEST);
    }
}
