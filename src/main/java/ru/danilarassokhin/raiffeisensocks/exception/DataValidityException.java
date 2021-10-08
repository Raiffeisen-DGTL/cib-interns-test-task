package ru.danilarassokhin.raiffeisensocks.exception;

import org.springframework.http.HttpStatus;

public class DataValidityException extends RequestException{
    public DataValidityException(String message) {
        super(message, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    public DataValidityException(String message, HttpStatus httpStatus) {
        super(message, httpStatus);
    }
}
