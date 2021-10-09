package ru.strelchm.raiffeisenchallenge.exception;

public class AlreadyExistedException extends RuntimeException {
    public AlreadyExistedException() {
        super("Already exists");
    }

    public AlreadyExistedException(String message) {
        super(message);
    }
}
