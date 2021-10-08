package ru.danilarassokhin.raiffeisensocks.exception;
import org.springframework.http.HttpStatus;

/**
 * Thrown if internal exception occurs
 */
public class InternalException extends RequestException{

    public InternalException(String message) {
        super(message, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    public InternalException(String message, HttpStatus httpStatus) {
        super(message, httpStatus);
    }
}
