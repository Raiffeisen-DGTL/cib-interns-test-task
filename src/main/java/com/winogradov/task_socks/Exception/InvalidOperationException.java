package com.winogradov.task_socks.Exception;

public class InvalidOperationException extends RuntimeException {
    public InvalidOperationException(String operation) {
        super(String.format("Operator for comparing the value of the amount of cotton in socks, "
                + "one value from: more Than, less Than, equal. "
                + "You choice: %s", operation));
    }
}