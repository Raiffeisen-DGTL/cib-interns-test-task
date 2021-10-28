package com.raiffeisensocks.app.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import javax.validation.ConstraintViolationException;
import java.util.*;

@RestControllerAdvice
public class SocksApiControllerAdvice {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> MethodArgumentNotValidExceptionHandler(MethodArgumentNotValidException ex) {
        List<FieldError> errors = ex.getFieldErrors();
        LinkedHashMap<String, List<String>> body = new LinkedHashMap<>();
        for (FieldError error: errors) {
            if (body.containsKey(error.getField())) {
                body.get(error.getField()).add(error.getDefaultMessage());
            } else {
                List<String> errorsMessage = new ArrayList<>();
                errorsMessage.add(error.getDefaultMessage());
                body.put(error.getField(), errorsMessage);
            }
        }
        return new ResponseEntity<>(Map.of("error", body), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<?> ConstraintViolationExceptionHandler(RuntimeException ex) {
        return new ResponseEntity<>(Map.of("error", ex.getMessage()), HttpStatus.BAD_REQUEST);
    }


    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<?> MethodArgumentTypeMismatchExceptionHandler(MethodArgumentTypeMismatchException ex) {
        String body = "Параметры запроса отсутствуют или имеют некорректный формат: " + ex.getName();
        return new ResponseEntity<>(Map.of("error", body), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MissingServletRequestParameterException.class)
    public ResponseEntity<?> MissingServletRequestParameterExceptionHandler(MissingServletRequestParameterException ex) {
        String body = "Пропущено поле " + ex.getParameterName();
        return new ResponseEntity<>(Map.of("error", body), HttpStatus.BAD_REQUEST);
    }
}
