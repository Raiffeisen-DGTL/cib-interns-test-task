package com.lazarev.socksapi.exception;

public class NotEnoughSocksOnStorehouseException extends RuntimeException {
    public NotEnoughSocksOnStorehouseException() {
    }

    public NotEnoughSocksOnStorehouseException(String message) {
        super(message);
    }

    public NotEnoughSocksOnStorehouseException(String message, Throwable cause) {
        super(message, cause);
    }

    public NotEnoughSocksOnStorehouseException(Throwable cause) {
        super(cause);
    }
}
