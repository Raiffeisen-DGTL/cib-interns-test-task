package ru.raiffeisen.socks.exception;

public class EmptyColorNameException extends RuntimeException {
    public EmptyColorNameException() {
        super("Color can not be blank");
    }
}
