package ru.raif.socks.exception;

public class NoSuchSocksQuantityException extends RuntimeException {

    public NoSuchSocksQuantityException() {
        super();
    }

    public NoSuchSocksQuantityException(String message) {
        super(message);
    }

    public NoSuchSocksQuantityException(String message, Throwable cause) {
        super(message, cause);
    }
}
