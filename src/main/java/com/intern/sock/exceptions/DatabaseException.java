package com.intern.sock.exceptions;
import com.intern.sock.enums.ErrorEnum;

public class DatabaseException extends Exception{
    private final ErrorEnum errors;
    public DatabaseException(ErrorEnum errors){
        this.errors=errors;
    }
}
