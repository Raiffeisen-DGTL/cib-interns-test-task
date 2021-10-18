package com.intern.sock.exceptions;
import lombok.Data;

@Data
public class ErrorResponse {
    private String code;
    private String message;

    public ErrorResponse(String code, String message){
        this.code = code;
        this.message = message;
    }

}
