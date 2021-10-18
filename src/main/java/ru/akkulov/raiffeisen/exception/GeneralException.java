package ru.akkulov.raiffeisen.exception;

/**
 * Базовый класс для всех пробрасываемых исключений
 */
public class GeneralException extends RuntimeException {

    public GeneralException(Exception e) {
        super(e);
    }

    public GeneralException(String msg) {
        super(msg);
    }

    public GeneralException(String format, Object... args) {
        super(String.format(format, args));
    }

    public GeneralException(String message, Exception e) {
        super(message, e);
    }
}
