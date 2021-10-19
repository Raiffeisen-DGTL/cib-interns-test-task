package com.raiffeisen.raiffeisen_test_task.exception;

public class RaiffeisenException {
    public static class ValidationException extends RuntimeException {
        public ValidationException(String message) {
            super(message);
        }
    }

    public static class NullResultException extends RuntimeException {
        public NullResultException(String message) {
            super(message);
        }
    }

    public static class IncorrectParameterException extends RuntimeException {
        public IncorrectParameterException(String message) {
            super(message);
        }
    }
}
