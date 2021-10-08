package ru.danilarassokhin.raiffeisensocks.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.danilarassokhin.raiffeisensocks.dto.ResponseDto;
import ru.danilarassokhin.raiffeisensocks.dto.SocksIncomeDto;
import ru.danilarassokhin.raiffeisensocks.model.SocksColor;
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

    @GetMapping(SOCKS.ALL_COLORS)
    @ResponseStatus(HttpStatus.OK)
    public ResponseDto<Set<SocksColor>> getAllSocksColors() {
        return new ResponseDto<>(
                Set.of(
                        SocksColor.values()
                )
        );
    }

    @PostMapping(SOCKS.INCOME)
    @ResponseStatus(HttpStatus.OK)
    public void incomeSocks(@RequestBody @Valid SocksIncomeDto socksIncomeDto) {
        sockService.income(socksIncomeDto);
    }


    @ExceptionHandler(value = ConstraintViolationException.class)
    private void handleConstraintViolationException(ConstraintViolationException exception) {

    }

}
