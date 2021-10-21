package ru.javabootcamp.handler;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

@ControllerAdvice
public class ResponseEntityHandler extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers,
                                                                  HttpStatus status, WebRequest request) {

        final Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String msg = error.getDefaultMessage();
            errors.put(fieldName, msg);
        });

        final Map<String, Object> result = new LinkedHashMap<>();
        result.put("timestamp", new Date());
        result.put("status", HttpStatus.BAD_REQUEST.value());
        result.put("error", HttpStatus.BAD_REQUEST.getReasonPhrase());
        result.put("path", ((ServletWebRequest) request).getRequest().getRequestURI());
        result.put("message", errors);

        return new ResponseEntity<Object>(result, HttpStatus.BAD_REQUEST);
    }
}