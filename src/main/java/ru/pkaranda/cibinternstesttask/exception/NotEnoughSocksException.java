package ru.pkaranda.cibinternstesttask.exception;

import ru.pkaranda.cibinternstesttask.model.Message;

public class NotEnoughSocksException extends BaseException{

    public NotEnoughSocksException() {
        super();
    }

    public NotEnoughSocksException(String message) {
        super(message);
    }

    public NotEnoughSocksException(String message, Throwable cause) {
        super(message, cause);
    }

    public NotEnoughSocksException(Throwable cause) {
        super(cause);
    }

    public NotEnoughSocksException(Message msg, Object... params) {
        super(msg, params);
    }
}
