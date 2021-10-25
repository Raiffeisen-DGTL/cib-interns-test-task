package com.example.socks.exception;

public class SocksException extends RuntimeException {
    public SocksException() {
        super();
    }

    public SocksException(String message) {
        super(message);
    }

    public SocksException(String message, Throwable cause) {
        super(message, cause);
    }

    public SocksException(Throwable cause) {
        super(cause);
    }
}
