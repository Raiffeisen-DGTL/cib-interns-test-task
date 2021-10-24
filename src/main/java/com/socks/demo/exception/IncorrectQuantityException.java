package com.socks.demo.exception;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.util.NoSuchElementException;

@Component
@Getter
@Setter
public class IncorrectQuantityException extends NoSuchElementException {

    private String errorMessage;
    private int errorCode;

    public IncorrectQuantityException() {
        errorMessage = "Неккоректно введены параметры! Количество пар должно быть больше 0!";
    }
}
