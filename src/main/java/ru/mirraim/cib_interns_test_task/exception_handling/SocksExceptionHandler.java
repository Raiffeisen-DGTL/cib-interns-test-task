package ru.mirraim.cib_interns_test_task.exception_handling;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class SocksExceptionHandler {

    @ExceptionHandler
    public ResponseEntity<SocksIncorrectData> handleException(
                            NoSuchSocksException exception) {
        SocksIncorrectData data = new SocksIncorrectData();
        data.setInfo(exception.getMessage());
        return new ResponseEntity<>(data, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    public ResponseEntity<SocksIncorrectData> handleException(
            Exception exception) {
        SocksIncorrectData data = new SocksIncorrectData();
        data.setInfo(exception.getMessage());
        return new ResponseEntity<>(data, HttpStatus.BAD_REQUEST);
    }
}
