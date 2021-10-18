package com.herokuapp.cibinternship.exception;

public class NotEnoughSocksException extends RuntimeException{
    public NotEnoughSocksException(String message) {
        super(message);
    }
}
