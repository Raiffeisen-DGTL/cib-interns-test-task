package com.lazarev.socksapi.exception;

public class SockNotFoundException extends RuntimeException {
    public SockNotFoundException() {
    }

    public SockNotFoundException(String message) {
        super(message);
    }

    public SockNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public SockNotFoundException(Throwable cause) {
        super(cause);
    }
}
