package ru.pkaranda.cibinternstesttask.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import ru.pkaranda.cibinternstesttask.component.MessageComponent;
import ru.pkaranda.cibinternstesttask.exception.*;
import ru.pkaranda.cibinternstesttask.model.Error;
import ru.pkaranda.cibinternstesttask.model.Message;

@RestControllerAdvice
@RequiredArgsConstructor
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    private final MessageComponent messageComponent;

    @ExceptionHandler(value = {
            ColorNotFoundException.class,
            NotEnoughSocksException.class,
            OperationException.class,
            NotValidCottonPartValueException.class,
            NotValidQuantityValueException.class
    })
    protected ResponseEntity<Object> handleBaseException(BaseException exception) {
        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
        if (exception instanceof ColorNotFoundException) {
            status = HttpStatus.NOT_FOUND;
        } else if (exception instanceof NotEnoughSocksException) {
            status = HttpStatus.BAD_REQUEST;
        } else if (exception instanceof OperationException) {
            status = HttpStatus.BAD_REQUEST;
        } else if (exception instanceof NotValidCottonPartValueException) {
            status = HttpStatus.BAD_REQUEST;
        } else if (exception instanceof NotValidQuantityValueException) {
            status = HttpStatus.BAD_REQUEST;
        }
        return buildErrorResult(status, exception.getMsg(), exception.getParams());
    }

    private ResponseEntity<Object> buildErrorResult(HttpStatus httpStatus, Message message, Object... params) {
        String text;
        if (params.length == 0) {
            text = messageComponent.getMessage(message.getText());
        } else {
            text = messageComponent.getMessageWithParams(message.getText(), params);
        }
        return new ResponseEntity<>(new Error(message.getCode(), text, params), httpStatus);
    }

}
