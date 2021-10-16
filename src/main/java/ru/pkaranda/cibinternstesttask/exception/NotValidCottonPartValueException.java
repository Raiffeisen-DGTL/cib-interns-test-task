package ru.pkaranda.cibinternstesttask.exception;

import ru.pkaranda.cibinternstesttask.model.Message;

public class NotValidCottonPartValueException extends BaseException{

    public NotValidCottonPartValueException() {
        super();
    }

    public NotValidCottonPartValueException(String message) {
        super(message);
    }

    public NotValidCottonPartValueException(String message, Throwable cause) {
        super(message, cause);
    }

    public NotValidCottonPartValueException(Throwable cause) {
        super(cause);
    }

    public NotValidCottonPartValueException(Message msg, Object... params) {
        super(msg, params);
    }

    @Override
    public Message getMsg() {
        return super.getMsg();
    }

    @Override
    public Object[] getParams() {
        return super.getParams();
    }
}
