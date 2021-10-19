package com.java.task.exception;

import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@Data
@ResponseStatus(value= HttpStatus.BAD_REQUEST)
public class OutcomeFromNotExistingSocksException extends RuntimeException {

    public OutcomeFromNotExistingSocksException(String message) {
        super(message);
    }
}
