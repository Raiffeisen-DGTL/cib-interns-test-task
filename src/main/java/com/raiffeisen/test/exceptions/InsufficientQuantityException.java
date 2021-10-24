package com.raiffeisen.test.exceptions;

public class InsufficientQuantityException extends RuntimeException{

    public InsufficientQuantityException(String message) {
        super(message);
    }
}
