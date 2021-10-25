package ru.raiffeisen.soksapp.exception;

public class NotEnoughSocsException extends RuntimeException{

    public NotEnoughSocsException(String message) {
        super(message);
    }
}
