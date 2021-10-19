package com.raiffeisen.task.exception;

import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@Data
@ResponseStatus(value= HttpStatus.BAD_REQUEST)
public class UnsupportableOperationException extends RuntimeException {
    private String receivedOperation;

    public UnsupportableOperationException(String message, String receivedOperation) {
        super(message);
        this.receivedOperation = receivedOperation;
    }
}
