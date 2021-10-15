package com.test.socksapp.exception;

public class ValueConstraintValidator {

    public static void positiveValidate(int v) {
        if (v >= 0) return;
        throw new SockInvalidArgumentException();
    }

    public static void percentValidate(int v) {
        if (v >= 0 && v <= 100) return;
        throw new SockInvalidArgumentException();
    }

    public static void notNullValidate(String v) {
        if (v != null) return;
        throw new SockInvalidArgumentException();
    }
}
