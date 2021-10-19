package com.kozlov.springjpa.spring_data_jpa.exception;

public class NegativeSocksQuantityException extends IllegalArgumentException{
    public NegativeSocksQuantityException() {
        super("Носков на складе меньше, чем нужно забрать. Их количество не может быть отрицательным.");
    }
}
