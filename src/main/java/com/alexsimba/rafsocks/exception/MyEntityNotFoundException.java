package com.alexsimba.rafsocks.exception;

public class MyEntityNotFoundException extends RuntimeException {

    public MyEntityNotFoundException() {
        super("Request parameters are missing or have an incorrect format");
    }
}