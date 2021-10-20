package com.raiffeisen.test.exceptions;

import lombok.Data;

import java.util.Date;

@Data
public class ApiError {

    private int statusCode;
    private String message;
    private Date date;

    public ApiError(int statusCode, String message){
        this.statusCode = statusCode;
        this.message = message;
        this.date = new Date();
    }
}
