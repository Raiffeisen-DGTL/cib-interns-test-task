package ru.pkaranda.cibinternstesttask.exception;

import ru.pkaranda.cibinternstesttask.model.Message;

public class OperationException extends BaseException {

    public OperationException() {
        super();
    }

    public OperationException(String message) {
        super(message);
    }

    public OperationException(String message, Throwable cause) {
        super(message, cause);
    }

    public OperationException(Throwable cause) {
        super(cause);
    }

    public OperationException(Message msg, Object... params) {
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
