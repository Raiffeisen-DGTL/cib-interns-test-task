package ru.danilarassokhin.raiffeisensocks.exception;

import org.springframework.http.HttpStatus;

/**
 * Thrown if data is not exists
 */
public class DataNotExistsException extends RequestException{

    public DataNotExistsException(String message) {
        super(message, HttpStatus.NOT_FOUND);
    }

    public DataNotExistsException(String message, HttpStatus httpStatus) {
        super(message, httpStatus);
    }
}
