package com.kozlov.springjpa.spring_data_jpa.exception;

public class NoRequiredParamException extends IllegalArgumentException {
    public NoRequiredParamException() {
        super("Все поля JSON должны присутствовать и быть заполнены.");
    }
}
