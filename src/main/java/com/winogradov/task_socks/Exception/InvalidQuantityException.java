package com.winogradov.task_socks.Exception;

public class InvalidQuantityException extends RuntimeException {

    public InvalidQuantityException(String message) {
        super(String.format("The number of pairs of socks, an integer greater than 0. You receive: %s", message));
    }
}
