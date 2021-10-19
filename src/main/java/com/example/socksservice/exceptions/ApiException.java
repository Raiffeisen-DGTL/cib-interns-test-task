package com.example.socksservice.exceptions;

public class ApiException extends RuntimeException{
    public ApiException(String message) {
        super(message);
    }
}
