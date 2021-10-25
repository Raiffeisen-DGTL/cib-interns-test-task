package com.example.socks.exception;

public class NotEnoughSocksException extends SocksException {
    public NotEnoughSocksException() {
        super();
    }

    public NotEnoughSocksException(String message) {
        super(message);
    }

    public NotEnoughSocksException(String message, Throwable cause) {
        super(message, cause);
    }

    public NotEnoughSocksException(Throwable cause) {
        super(cause);
    }
}
