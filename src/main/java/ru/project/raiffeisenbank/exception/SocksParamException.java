package ru.project.raiffeisenbank.exception;

public class SocksParamException extends RuntimeException {
    private String message;

    public SocksParamException() { }

    public SocksParamException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
