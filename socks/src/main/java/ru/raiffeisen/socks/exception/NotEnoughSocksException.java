package ru.raiffeisen.socks.exception;

public class NotEnoughSocksException extends RuntimeException {
    public NotEnoughSocksException(long requiredQuantity, long realQuantity) {
        super("Required " + requiredQuantity + " socks, but have only " + realQuantity);
    }
}
