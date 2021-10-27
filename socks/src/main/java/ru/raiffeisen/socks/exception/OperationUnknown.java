package ru.raiffeisen.socks.exception;

public class OperationUnknown extends RuntimeException {
    public OperationUnknown(String operation) {
        super("Operation: " + operation + " unknown");
    }
}
