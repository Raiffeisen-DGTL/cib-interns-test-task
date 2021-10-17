package com.raiffeisen.socks.exceptions;

public class NotFoundSockException extends RuntimeException {
    public NotFoundSockException(String message) {
        super(message);
    }
}
