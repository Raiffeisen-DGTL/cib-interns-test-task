package com.kozlov.springjpa.spring_data_jpa.exception;

public class EmptySocksStorageException extends NullPointerException {
    public EmptySocksStorageException() {
        super("В базе данных отсутствуют объекты. Сперва поместите их туда.");
    }
}
