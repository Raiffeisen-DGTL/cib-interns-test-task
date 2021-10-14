package com.example.company.exception;

public class InsufficientDataException extends RuntimeException{
    public InsufficientDataException(String message) {
        super(message);
    }
}
