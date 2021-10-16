package com.raiffeisen.api.socks.exceptions;

public class BadArgumentException extends RuntimeException{
    public BadArgumentException(String operation){
        super("Bad argument - " + operation + ". Operations may be only moreThan, lessThan, equal");
    }
}
