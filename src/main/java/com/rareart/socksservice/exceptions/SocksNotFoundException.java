package com.rareart.socksservice.exceptions;

public class SocksNotFoundException extends Exception {
    public SocksNotFoundException(String message) {
        super(message);
    }

    public SocksNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
