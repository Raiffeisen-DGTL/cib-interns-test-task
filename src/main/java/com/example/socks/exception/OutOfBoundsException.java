package com.example.socks.exception;

public class OutOfBoundsException extends SocksException {
    public OutOfBoundsException() {
    }

    public OutOfBoundsException(String message) {
        super(message);
    }

    public OutOfBoundsException(String message, Throwable cause) {
        super(message, cause);
    }

    public OutOfBoundsException(Throwable cause) {
        super(cause);
    }
}
