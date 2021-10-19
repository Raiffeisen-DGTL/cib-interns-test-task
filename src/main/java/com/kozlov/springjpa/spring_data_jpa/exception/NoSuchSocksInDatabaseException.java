package com.kozlov.springjpa.spring_data_jpa.exception;

public class NoSuchSocksInDatabaseException extends IllegalArgumentException{
    public NoSuchSocksInDatabaseException() {
        super("В базе данных нет носков с такими параметрами, отпуск невозможен.");
    }
}
