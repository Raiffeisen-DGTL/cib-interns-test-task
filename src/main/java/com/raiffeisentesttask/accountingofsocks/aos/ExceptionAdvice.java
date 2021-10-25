package com.raiffeisentesttask.accountingofsocks.aos;

import com.raiffeisentesttask.accountingofsocks.aos.entity.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionAdvice {

    @ExceptionHandler({HttpMessageNotReadableException.class, MissingServletRequestParameterException.class})
    public ResponseEntity<Response> handleExceptionBadRequest(){
        Response response = new Response("HTTP 400 — параметры запроса отсутствуют или имеют некорректный формат");
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Response> handleExceptionServerError(){
        Response response = new Response("HTTP 500 — произошла ошибка, не зависящая от вызывающей стороны");
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
