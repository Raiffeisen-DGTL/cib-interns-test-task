package ru.danilarassokhin.raiffeisensocks.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.danilarassokhin.raiffeisensocks.dto.ResponseDto;
import ru.danilarassokhin.raiffeisensocks.dto.SocksIncomeDto;
import ru.danilarassokhin.raiffeisensocks.exception.DataValidityException;
import ru.danilarassokhin.raiffeisensocks.service.SocksService;

import javax.validation.ConstraintViolationException;
import javax.validation.Valid;

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
    public void incomeSocks(@RequestBody @Valid SocksIncomeDto socksIncomeDto) throws DataValidityException {
        sockService.income(socksIncomeDto);
    }


    @ExceptionHandler(value = {ConstraintViolationException.class, DataValidityException.class,
            HttpMessageNotReadableException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    private ResponseDto<String> handleConstraintViolationException(Exception exception) {
        return new ResponseDto<>("Error occurred", exception.getMessage());
    }

}
