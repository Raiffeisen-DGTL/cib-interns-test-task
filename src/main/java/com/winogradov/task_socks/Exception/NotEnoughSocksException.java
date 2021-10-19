package com.winogradov.task_socks.Exception;

public class NotEnoughSocksException extends RuntimeException{

    public NotEnoughSocksException(String message){
        super(String.format("There are not so many socks in stock: %s", message));
    }
}
