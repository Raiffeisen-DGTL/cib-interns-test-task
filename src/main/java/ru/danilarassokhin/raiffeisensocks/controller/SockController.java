package ru.danilarassokhin.raiffeisensocks.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import ru.danilarassokhin.raiffeisensocks.dto.ResponseDto;
import ru.danilarassokhin.raiffeisensocks.dto.SocksIncomeDto;
import ru.danilarassokhin.raiffeisensocks.dto.SocksOutcomeDto;
import ru.danilarassokhin.raiffeisensocks.dto.SocksSearchDto;
import ru.danilarassokhin.raiffeisensocks.exception.DataNotExistsException;
import ru.danilarassokhin.raiffeisensocks.exception.DataValidityException;
import ru.danilarassokhin.raiffeisensocks.exception.InternalException;
import ru.danilarassokhin.raiffeisensocks.model.Socks;
import ru.danilarassokhin.raiffeisensocks.service.SocksService;

import javax.validation.ConstraintViolationException;
import javax.validation.Valid;

import java.util.Set;

import static ru.danilarassokhin.raiffeisensocks.Url.API_ENDPOINT;
import static ru.danilarassokhin.raiffeisensocks.Url.SOCKS;

@RestController
@RequestMapping(API_ENDPOINT + SOCKS.ENDPOINT)
@Validated
public class SockController {

    private SocksService sockService;

    @Autowired
    public SockController(SocksService sockService) {
        this.sockService = sockService;
    }

    @PostMapping(SOCKS.INCOME)
    @ResponseStatus(HttpStatus.OK)
    public ResponseDto<SocksIncomeDto> incomeSocks(@RequestBody @Valid SocksIncomeDto socksIncomeDto)
            throws DataValidityException, InternalException {
        return new ResponseDto<>("Success",
                sockService.income(socksIncomeDto)
        );
    }

    @PostMapping(SOCKS.OUTCOME)
    @ResponseStatus(HttpStatus.OK)
    public ResponseDto<SocksOutcomeDto> outcomeSocks(@RequestBody @Valid SocksOutcomeDto socksOutcomeDto)
            throws DataValidityException, DataNotExistsException, InternalException {
        return new ResponseDto<>("Success",
                sockService.outcome(socksOutcomeDto)
        );
    }

    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public ResponseDto<Long> searchSocks(@RequestBody @Valid SocksSearchDto socksSearchDto)
            throws DataValidityException {
        return new ResponseDto<>("Success",
                sockService.countSocks(socksSearchDto)
        );
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseDto<String> handleConstraintViolationException(MethodArgumentNotValidException exception) {
        return new ResponseDto<>("Error", exception.getFieldError().getDefaultMessage());
    }

    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseDto<String> handleConstraintViolationException(ConstraintViolationException exception) {
        return new ResponseDto<>("Error", exception.getMessage());
    }


    @ExceptionHandler(DataValidityException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseDto<String> handleDataValidityException(DataValidityException exception) {
        return new ResponseDto<>("Error", exception.getMessage());
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseDto<String> handleHttpMessageNotReadableException(HttpMessageNotReadableException exception) {
        return new ResponseDto<>("Error", exception.getMessage());
    }

    @ExceptionHandler(DataNotExistsException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseDto<String> handleDataNotExistsException(DataNotExistsException exception) {
        return new ResponseDto<>("Error", exception.getMessage());
    }

    @ExceptionHandler(InternalException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseDto<String> handleInternalException(InternalException exception) {
        return new ResponseDto<>("Error", exception.getMessage());
    }

}
