package ru.raiffeisen.socks.exception;

public class SocksNotFoundException extends RuntimeException {
    public SocksNotFoundException(String color, int cottonPart) {
        super("Socks with color: " + color + " and cotton percent: " + cottonPart + "% not found");
    }
}
