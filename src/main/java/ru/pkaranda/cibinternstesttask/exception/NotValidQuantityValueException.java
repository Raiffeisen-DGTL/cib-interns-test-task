package ru.pkaranda.cibinternstesttask.exception;

import ru.pkaranda.cibinternstesttask.model.Message;

public class NotValidQuantityValueException extends BaseException{

    public NotValidQuantityValueException() {
        super();
    }

    public NotValidQuantityValueException(String message) {
        super(message);
    }

    public NotValidQuantityValueException(String message, Throwable cause) {
        super(message, cause);
    }

    public NotValidQuantityValueException(Throwable cause) {
        super(cause);
    }

    public NotValidQuantityValueException(Message msg, Object... params) {
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
