package com.oleg.socks.exception;

import com.oleg.socks.entity.ApiErrorResponse;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;


@ControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)
@ResponseBody
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = {NotValidInputException.class})
    protected ResponseEntity<Object> handleError(NotValidInputException ex, WebRequest request) {
        logger.error("Exception is occurred", ex);
        return handleExceptionInternal(ex, ApiErrorResponse.builder()
                .errorCode("VALIDATION_ERROR")
                .errorMessage(ex.getMessage())
                .build(), new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }

    @ExceptionHandler(value = {NotFoundException.class})
    protected ResponseEntity<Object> handleUserNotFoundError(NotFoundException ex, WebRequest request) {
        logger.error("Exception is occurred", ex);
        return handleExceptionInternal(ex, ApiErrorResponse.builder()
                .errorCode("NOT_FOUND_ERROR")
                .errorMessage(ex.getMessage())
                .build(), new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }

}
