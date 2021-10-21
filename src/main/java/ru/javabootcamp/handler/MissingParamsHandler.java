package ru.javabootcamp.handler;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.ServletWebRequest;

import java.util.Map;

@ControllerAdvice
public class MissingParamsHandler extends BaseParamsHandler {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MissingServletRequestParameterException.class)
    @ResponseBody
    public Map<String, Object> handleMissingParams(MissingServletRequestParameterException e,
                                                   ServletWebRequest request) {
        return super.handleParamsException(e, request);
    }
}
