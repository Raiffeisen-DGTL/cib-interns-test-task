package ru.raiffeisen.dgtl.cib.intern.task.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class NotFoundSocks extends RuntimeException {
    public NotFoundSocks() {
        super();
    }

    public NotFoundSocks(String message, Throwable cause) {
        super(message, cause);
    }

    public NotFoundSocks(String message) {
        super(message);
    }

    public NotFoundSocks(Throwable cause) {
        super(cause);
    }
}



