package ru.raiffeisen.dgtl.cib.intern.task.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class NotFoundOperation extends RuntimeException {
    public NotFoundOperation() {
        super();
    }

    public NotFoundOperation(String message, Throwable cause) {
        super(message, cause);
    }

    public NotFoundOperation(String message) {
        super(message);
    }

    public NotFoundOperation(Throwable cause) {
        super(cause);
    }
}



