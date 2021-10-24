package com.fulkin.sockApp.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author Fulkin
 * Created on 20.10.2021
 */
public class BadRequestException extends Exception{
    public BadRequestException(String message) {
        super(message);
    }


}
