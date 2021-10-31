package me.mlnn.raif.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "Received data is invalid")
public class InvalidDataException extends RuntimeException {
    public InvalidDataException() {
        super("Received data is invalid");
    }
}
