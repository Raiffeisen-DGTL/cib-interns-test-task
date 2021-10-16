package com.raiffeisen.api.socks.exceptions;

public class NoSuchSocksFoundException extends IllegalArgumentException{
    public NoSuchSocksFoundException(String quantity){
        super("There are only " + quantity + " socks in store");
    }

    public NoSuchSocksFoundException(){
        super("Not find combination of color and cottonPart");
    }
}
