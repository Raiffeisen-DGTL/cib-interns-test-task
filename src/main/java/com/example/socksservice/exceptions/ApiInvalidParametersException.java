package com.example.socksservice.exceptions;

import org.springframework.http.HttpStatus;

public class ApiInvalidParametersException extends ApiException {

    public ApiInvalidParametersException(String ex) {
        super(ex);
    }
}
