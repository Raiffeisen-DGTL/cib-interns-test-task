package com.example.springsocks.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * ValidationException.
 *
 * @author Alexander_Tupchin
 */
@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class ValidationException extends RuntimeException {

    private String message;

    public ValidationException(String message) {
    }

    public String getMessage() {
        return message;
    }
}
