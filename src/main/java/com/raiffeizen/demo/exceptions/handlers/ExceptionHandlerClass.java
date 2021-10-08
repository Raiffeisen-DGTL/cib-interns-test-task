package com.raiffeizen.demo.exceptions.handlers;

import com.raiffeizen.demo.exceptions.BadRequestException;
import com.raiffeizen.demo.exceptions.InternalServerErrorException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ExceptionHandlerClass extends ResponseEntityExceptionHandler {

    @ExceptionHandler(BadRequestException.class)
    protected ResponseEntity<String> handleBadRequestException(){
        return new ResponseEntity<>(
                "Параметры запроса отсутствуют или имеют некорректный формат", HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(InternalServerErrorException.class)
    protected ResponseEntity<String> handleInternalServerErrorException(){
        return new ResponseEntity<>(
                "Произошла ошибка, не зависящая от вызывающей стороны (например, база данных недоступна)", HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
