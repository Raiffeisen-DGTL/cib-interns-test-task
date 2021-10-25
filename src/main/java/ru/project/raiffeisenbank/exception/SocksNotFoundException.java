package ru.project.raiffeisenbank.exception;

public class SocksNotFoundException extends RuntimeException {
    private String message;

    public SocksNotFoundException() { }

    public SocksNotFoundException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
