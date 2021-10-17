package com.github.furetur.raiffeisentask.exceptions;

public class NotEnoughSocksException extends IllegalArgumentException {
    public NotEnoughSocksException() {
        super("Not enough socks in stock.");
    }
}
