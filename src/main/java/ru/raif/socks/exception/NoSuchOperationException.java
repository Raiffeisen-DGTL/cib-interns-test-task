package ru.raif.socks.exception;

public class NoSuchOperationException extends RuntimeException {

    public NoSuchOperationException(){
        super();
    }

    public NoSuchOperationException(String message){
        super(message);
    }

    public NoSuchOperationException(String message, Throwable cause){
        super(message, cause);
    }
}
