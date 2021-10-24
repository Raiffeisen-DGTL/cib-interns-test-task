package com.raiffeisen.test.exceptions;

public class InvalidParamsException extends RuntimeException {

    public InvalidParamsException(String message) {
        super(message);
    }
}
