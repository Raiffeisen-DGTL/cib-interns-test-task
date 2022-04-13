package com.example.socks.Util;

import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.core.convert.ConversionFailedException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.validation.ConstraintViolationException;

@ControllerAdvice(annotations = SocksControllerExceptionHandler.class)
public class CustomExceptionAdvice {
    private String myEx = "Параметры запроса отсутствуют или имеют некорректный формат";

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<Response> handleConstraint(ConstraintViolationException ex) throws JSONException {
        Response response = new Response(ex.getMessage(), myEx);
        return ResponseEntity
                .badRequest()
                .body(response);

    }

    @ExceptionHandler(MissingServletRequestParameterException.class)
    public ResponseEntity<Response> missingServletRequestParameterException(MissingServletRequestParameterException ex) {
        Response response = new Response(ex.getMessage(), myEx);
        return ResponseEntity
                .badRequest()
                .body(response);
    }

    @ExceptionHandler(ConversionFailedException.class)
    public ResponseEntity<Response> handleConflict(RuntimeException ex) {
        Response response = new Response(ex.getMessage(), myEx);
        return ResponseEntity
                .badRequest()
                .body(response);
    }


}
