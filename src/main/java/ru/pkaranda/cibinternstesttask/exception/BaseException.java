package ru.pkaranda.cibinternstesttask.exception;

import ru.pkaranda.cibinternstesttask.model.Message;

public abstract class BaseException extends RuntimeException{

    private Message msg;
    private Object[] params;

    public BaseException() {
    }

    public BaseException(String message) {
        super(message);
    }

    public BaseException(String message, Throwable cause) {
        super(message, cause);
    }

    public BaseException(Throwable cause) {
        super(cause);
    }

    public BaseException(Message msg, Object... params) {
        this.msg = msg;
        this.params = params;
    }

    public Message getMsg() {
        return msg;
    }

    public Object[] getParams() {
        return params;
    }
}
