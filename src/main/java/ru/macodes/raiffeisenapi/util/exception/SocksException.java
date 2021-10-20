package ru.macodes.raiffeisenapi.util.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class SocksException extends RuntimeException {
    public SocksException(String message) {
        super(message);    }
}
