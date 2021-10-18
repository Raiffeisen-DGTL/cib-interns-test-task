package com.intern.sock.enums;

public enum ErrorEnum {
    IncorrectURL("IncorrectURL"), NullResultForRequest("NullResultForRequest");
    private final String error;
    ErrorEnum(String error){
        this.error = error;
    }
}
