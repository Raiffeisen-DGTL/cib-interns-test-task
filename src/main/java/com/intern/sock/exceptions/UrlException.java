package com.intern.sock.exceptions;

import com.intern.sock.enums.ErrorEnum;

public class UrlException extends Exception {
    private final ErrorEnum errors;
    public UrlException(ErrorEnum errors){
        this.errors = errors;
    }
}
