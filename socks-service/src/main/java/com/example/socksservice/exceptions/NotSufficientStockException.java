package com.example.socksservice.exceptions;

public class NotSufficientStockException extends RuntimeException {
    public NotSufficientStockException() {
        super("Недостаток товарного запаса");
    }
}
