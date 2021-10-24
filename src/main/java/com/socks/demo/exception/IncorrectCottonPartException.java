package com.socks.demo.exception;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.util.NoSuchElementException;

@Component
@Getter
@Setter
public class IncorrectCottonPartException extends NoSuchElementException {

    private String errorMessage;
    private int errorCode;

    public IncorrectCottonPartException() {
        errorMessage = "Неккоректно введены параметры! Количество хлопка должно быть больше 0%, но меньше 100%";
    }

}
