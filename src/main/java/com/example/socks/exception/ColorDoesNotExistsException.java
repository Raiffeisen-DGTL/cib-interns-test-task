package com.example.socks.exception;

public class ColorDoesNotExistsException extends SocksException{
    public ColorDoesNotExistsException() {
        super();
    }

    public ColorDoesNotExistsException(String message) {
        super(message);
    }

    public ColorDoesNotExistsException(String message, Throwable cause) {
        super(message, cause);
    }

    public ColorDoesNotExistsException(Throwable cause) {
        super(cause);
    }
}
