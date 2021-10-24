package com.socks.demo.exception;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
@Getter
@Setter
public class IncorrectParametersException extends IOException {

    private String errorMessage;
    private int errorCode;

    public IncorrectParametersException() {
        errorMessage = "Неккоректно введены параметры! Пожалуйста исправьте запрос";
    }

}
