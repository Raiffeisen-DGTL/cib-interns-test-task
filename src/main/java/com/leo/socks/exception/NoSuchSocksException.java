package com.leo.socks.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class NoSuchSocksException extends RuntimeException{
    public NoSuchSocksException() {
        super();
    }

    public NoSuchSocksException(String message) {
        super(message);
    }

    public NoSuchSocksException(String message, Throwable cause) {
        super(message, cause);
    }

    public NoSuchSocksException(Throwable cause) {
        super(cause);
    }

    protected NoSuchSocksException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
