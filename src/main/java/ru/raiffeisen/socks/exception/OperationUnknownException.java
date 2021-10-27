package ru.raiffeisen.socks.exception;

public class OperationUnknownException extends RuntimeException {
    public OperationUnknownException(String operation) {
        super("Operation: " + operation + " unknown");
    }
}
