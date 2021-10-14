package ru.raiffeisen.dgtl.cib.intern.task.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class NotEnoughSocksQuantityException extends RuntimeException {
    public NotEnoughSocksQuantityException() {
        super();
    }

    public NotEnoughSocksQuantityException(String message, Throwable cause) {
        super(message, cause);
    }

    public NotEnoughSocksQuantityException(String message) {
        super(message);
    }

    public NotEnoughSocksQuantityException(Throwable cause) {
        super(cause);
    }
}



