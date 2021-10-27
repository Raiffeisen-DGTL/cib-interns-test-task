package com.example.socksApi.exceptions;

public class ApiException extends RuntimeException{
    public ApiException(String message) {
        super(message);
    }

}
