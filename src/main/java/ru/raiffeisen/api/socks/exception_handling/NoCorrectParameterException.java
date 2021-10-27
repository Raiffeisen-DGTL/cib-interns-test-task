package ru.raiffeisen.api.socks.exception_handling;

public class NoCorrectParameterException extends RuntimeException {
    public NoCorrectParameterException(String message) {
        super(message);
    }
}
