package com.kozlov.springjpa.spring_data_jpa.exception;

public class IncorrectRequestParamException extends IllegalArgumentException{
    public IncorrectRequestParamException() {
        super("Параметры запроса некорректные");
    }
}
