package com.example.socksservice.exceptions;

public class NotFoundSockException extends RuntimeException {
    public NotFoundSockException() {
        super("Не найден носок :(");
    }
}
