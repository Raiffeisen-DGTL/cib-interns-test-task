package com.example.demo.exceptions;

public class IncorrectParametersException extends RuntimeException {
    public IncorrectParametersException(String message) {
        super(message);
    }
}
