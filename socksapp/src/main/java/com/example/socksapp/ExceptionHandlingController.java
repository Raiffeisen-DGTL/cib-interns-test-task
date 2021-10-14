package com.example.socksapp;

import org.springframework.core.convert.ConversionFailedException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;


import javax.validation.ConstraintViolationException;

@ControllerAdvice
public class ExceptionHandlingController {

    /**
     * Параметры запроса отсутсвуют или имеют некорректный формат - ConstraintViolationException
     * ошибка конвертации String в Comparison - ConversionFailedException
     * Ограничения параметеров в Post запросе - MethodArgumentNotValidException
     * (если передали количество носков меньше 1, или % хлопка за пределами значений от 0 до 100)
     */
    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    @ExceptionHandler({ConstraintViolationException.class,
            ConversionFailedException.class, MethodArgumentNotValidException.class})
    public void handleConflict(){
    }


}
