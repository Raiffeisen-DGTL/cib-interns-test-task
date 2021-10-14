package com.example.socksspring.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class BadRequestException  extends RuntimeException {
    public BadRequestException(String message) {
        super("wrong type paratemers: " + message);
    }

}
