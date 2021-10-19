package ru.hlem.storesocksraif.exceptions;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ExceptionControllerAdvice extends ResponseEntityExceptionHandler {
    private static final String INCOMING_REQUEST_FAILED = "Incoming request failed:";
    private static final Logger LOGGER = LoggerFactory.getLogger(ExceptionControllerAdvice.class);

    private final MessageSource source;

    @Autowired
    public ExceptionControllerAdvice(final MessageSource messageSource) {
        source = messageSource;
    }

    @ExceptionHandler(value = IllegalArgumentException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public String illegalArgumentRequest(IllegalArgumentException ex) {
        String message = INCOMING_REQUEST_FAILED + ex.getMessage();
        LOGGER.error(INCOMING_REQUEST_FAILED, ex);
        return message;
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        LOGGER.error(INCOMING_REQUEST_FAILED, ex);
        return new ResponseEntity<>(ex.getMessage(), headers, status);
    }

    @ExceptionHandler(Throwable.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public String internalServerError(final Exception ex) {
        LOGGER.error(INCOMING_REQUEST_FAILED, ex);
        String message =
                source.getMessage("exception.INTERNAL_SERVER_ERROR", null, LocaleContextHolder.getLocale());
        return message;
    }
}
