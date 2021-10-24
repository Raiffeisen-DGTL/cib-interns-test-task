package ru.raif.socks.exception;

public class SocksNotFoundException extends RuntimeException {

    public SocksNotFoundException() {
        super();
    }

    public SocksNotFoundException(String message) {
        super(message);
    }

    public SocksNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

}
