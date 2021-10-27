package ru.raiffeisen.socksapp.Exception;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Component
@Getter
@Setter
public class IncorrectParametersException extends RuntimeException {
    private String errorMessage;
    private String errorParam;
    private int errorCode;

    public IncorrectParametersException() {
        this.errorMessage = "Некорректно введены параметры";
    }

    public IncorrectParametersException(String errorMessage, String errorParam) {
        this.errorMessage = errorMessage;
        this.errorParam = errorParam;
    }


}
