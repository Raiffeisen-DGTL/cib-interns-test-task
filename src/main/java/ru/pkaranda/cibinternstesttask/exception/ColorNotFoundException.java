package ru.pkaranda.cibinternstesttask.exception;

import ru.pkaranda.cibinternstesttask.model.Message;

public class ColorNotFoundException extends BaseException{

    public ColorNotFoundException() {
        super();
    }

    public ColorNotFoundException(String message) {
        super(message);
    }

    public ColorNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public ColorNotFoundException(Throwable cause) {
        super(cause);
    }

    public ColorNotFoundException(Message msg, Object... params) {
        super(msg, params);
    }
}
