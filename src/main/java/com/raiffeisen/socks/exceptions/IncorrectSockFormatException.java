package com.raiffeisen.socks.exceptions;

public class IncorrectSockFormatException extends RuntimeException {
    public IncorrectSockFormatException(String message){
        super(message);
    }
}
