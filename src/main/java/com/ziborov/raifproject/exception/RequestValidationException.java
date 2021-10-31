package com.ziborov.raifproject.exception;

public class RequestValidationException extends RuntimeException {

    public RequestValidationException(String errorMessage) {
        super(errorMessage);
    }

}