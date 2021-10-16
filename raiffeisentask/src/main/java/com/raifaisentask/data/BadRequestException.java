package com.raifaisentask.data;

public class BadRequestException extends Exception{
    public BadRequestException(){
        super("BAD REQUEST");
    }
    public BadRequestException(String msg){
        super("BAD REQUEST: "+msg);
    }
}
