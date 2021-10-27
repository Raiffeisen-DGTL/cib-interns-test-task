package ru.raiffeisen.socks.exception;

public class ColorNotFoundException extends RuntimeException {
    public ColorNotFoundException(String color){
        super(color + " not found");
    }
}
