package com.raiffeisen.test.exceptions;

public class NoSuchOperationException extends RuntimeException {

    public NoSuchOperationException(String message) {
        super(message);
    }
}
