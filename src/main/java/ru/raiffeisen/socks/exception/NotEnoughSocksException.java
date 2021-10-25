package ru.raiffeisen.socks.exception;

public class NotEnoughSocksException extends RuntimeException {
    public NotEnoughSocksException() {
        super("На складе недостаточное количество носков");
    }
}
