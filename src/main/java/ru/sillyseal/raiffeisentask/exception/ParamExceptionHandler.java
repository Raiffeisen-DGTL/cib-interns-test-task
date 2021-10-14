package ru.sillyseal.raiffeisentask.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.validation.ConstraintViolationException;

@ControllerAdvice
public class ParamExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(value = { ConstraintViolationException.class})
    protected ResponseEntity<Object> handleConflict(RuntimeException ex, WebRequest request){
        String bodyOfResponse = "HTTP 400 — Параметры запроса отсутствуют или имеют некорректный формат.";
        return handleExceptionInternal(ex, bodyOfResponse,
                new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }
}
