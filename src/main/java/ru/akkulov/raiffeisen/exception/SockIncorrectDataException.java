package ru.akkulov.raiffeisen.exception;

/**
 * Исключение невалидности введенных данных.
 */
public class SockIncorrectDataException extends GeneralException {
    public SockIncorrectDataException(String msg) {
        super(msg);
    }
}
