package ru.danilarassokhin.raiffeisensocks.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.danilarassokhin.raiffeisensocks.dto.SockIncomeDto;
import ru.danilarassokhin.raiffeisensocks.model.SockColor;
import ru.danilarassokhin.raiffeisensocks.service.SockService;

import javax.validation.ConstraintViolationException;
import javax.validation.Valid;

import static ru.danilarassokhin.raiffeisensocks.Url.API_ENDPOINT;
import static ru.danilarassokhin.raiffeisensocks.Url.SOCK;

@RestController
@RequestMapping(API_ENDPOINT + SOCK.ENDPOINT)
@Validated
public class SockController {

    private SockService sockService;

    @Autowired
    public SockController(SockService sockService) {
        this.sockService = sockService;
    }

    @GetMapping(SOCK.ALL_COLORS)
    public ResponseEntity getAllSockColors() {

    }

    @PostMapping(SOCK.INCOME)
    public ResponseEntity sockIncome(@RequestBody @Valid SockIncomeDto sockIncomeDto) {

    }

    @ExceptionHandler(value = ConstraintViolationException.class)
    private void handleConstraintViolationException(ConstraintViolationException exception) {

    }

}
