package ru.mirraim.cib_interns_test_task.exception_handling;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class SocksExceptionHandler {

    @ExceptionHandler
    public ResponseEntity<SocksInfo> handleException(
                            NoSuchSocksException exception) {
        SocksInfo data = new SocksInfo();
        data.setInfo(exception.getMessage());
        return new ResponseEntity<>(data, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    public ResponseEntity<SocksInfo> handleException(
            MissingServletRequestParameterException exception) {
        SocksInfo data = new SocksInfo();
        data.setInfo(exception.getMessage());
        return new ResponseEntity<>(data, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    public ResponseEntity<SocksInfo> handleException(
            Exception exception) {
        SocksInfo data = new SocksInfo();
        data.setInfo(exception.getMessage());
        return new ResponseEntity<>(data, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
