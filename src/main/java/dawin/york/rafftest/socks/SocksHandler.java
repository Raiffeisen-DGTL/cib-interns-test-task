package dawin.york.rafftest.socks;

import dawin.york.rafftest.socks.exceptions.InvalidParameterException;
import dawin.york.rafftest.socks.exceptions.SocksExceptionHandler;
import dawin.york.rafftest.socks.tos.Errors;
import dawin.york.rafftest.socks.tos.ExceptionResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice(annotations = SocksExceptionHandler.class)
@Slf4j
public class SocksHandler {


    @ExceptionHandler(InvalidParameterException.class)
    public ResponseEntity<ExceptionResponse> handleException(InvalidParameterException ex){
        return new ResponseEntity<>(new ExceptionResponse(ex.getMessage(), Errors.PARAMETER_ERROR), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ExceptionResponse> handleException(Exception ex){
        log.debug(ex.getMessage(), ex);
        return new ResponseEntity<>(new ExceptionResponse(ex.getMessage(), Errors.SERVICE_ERROR), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
