package ru.testJava.controller;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.testJava.dto.SocksDto;
import ru.testJava.exception.OutcomeImpossibleException;
import ru.testJava.service.SocksService;

import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

@Validated
@RestController
@RequestMapping("api/socks")
public class SocksController {

    private final SocksService service;

    public SocksController(SocksService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<String> getAllSocks(@RequestParam @NotBlank String color, @RequestParam @NotBlank String operation,
                                              @RequestParam @Min(0) @Max(100) int cottonPart) {
        int quantity = service.getAll(color, operation, cottonPart);
        return new ResponseEntity<>(Integer.toString(quantity), HttpStatus.OK);
    }

    @PostMapping(value = "income")
    public ResponseEntity<String> income(@Valid @RequestBody SocksDto socks) {
        service.income(socks);
        return new ResponseEntity<>("Income: " + socks, HttpStatus.OK);
    }

    @PostMapping(value = "outcome")
    public ResponseEntity<String> outcome(@Valid @RequestBody SocksDto socks) throws OutcomeImpossibleException {
        service.outcome(socks);
        return new ResponseEntity<>("Outcome: " + socks, HttpStatus.OK);
    }
}
