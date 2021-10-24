package com.socks.demo.exception;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.util.NoSuchElementException;

@Component
@Getter
@Setter
public class IncorrectColorException extends NoSuchElementException {

    private String errorMessage;
    private int errorCode;

    public IncorrectColorException() {
        errorMessage = "Неккоректно введены параметры! Пожалуйста исправьте запрос";
    }
}
