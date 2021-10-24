package ru.raif.socks.exception;

public class BadSocksException extends RuntimeException {

    public BadSocksException(){
        super();
    }

    public BadSocksException(String message){
        super(message);
    }

    public BadSocksException(String message, Throwable cause){
        super(message, cause);
    }
}
