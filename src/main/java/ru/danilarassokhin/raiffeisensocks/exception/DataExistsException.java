package ru.danilarassokhin.raiffeisensocks.exception;

import org.springframework.http.HttpStatus;

/**
 * Thrown if data is already exists
 */
public class DataExistsException extends RequestException{

    public DataExistsException(String message) {
        super(message, HttpStatus.CONFLICT);
    }

    public DataExistsException(String message, HttpStatus httpStatus) {
        super(message, httpStatus);
    }
}
