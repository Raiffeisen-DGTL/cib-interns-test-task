package com.winogradov.task_socks.Exception;

public class InvalidCottonPartException extends RuntimeException {

    public InvalidCottonPartException(String message) {
        super(String.format(
                "Percentage of cotton in the composition of socks from 0 to 100. You receive :%s",
                message));
    }
}
