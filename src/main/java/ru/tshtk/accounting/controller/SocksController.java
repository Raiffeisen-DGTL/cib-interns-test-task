package ru.tshtk.accounting.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.tshtk.accounting.dto.SocksRequest;
import ru.tshtk.accounting.exception.OutcomeImpossibleException;
import ru.tshtk.accounting.service.SocksService;

import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

@Validated
@RequestMapping("api/socks")
@RestController
public class SocksController {
    private final SocksService socksService;

    @Autowired
    public SocksController(SocksService socksService) {
        this.socksService = socksService;
    }

    @PostMapping(value="income")
    public ResponseEntity<String> income(@Valid @RequestBody SocksRequest socksIn) {
        socksService.registerIncome(socksIn);
        return new ResponseEntity<>("Оприходовано: " + socksIn, HttpStatus.OK);
    }

    @PostMapping(value="outcome")
    public ResponseEntity<String> outcome(@Valid @RequestBody SocksRequest socksOut) throws OutcomeImpossibleException {
        socksService.registerOutcome(socksOut);
        return new ResponseEntity<>("Отпущено: " + socksOut, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<String> requestQuantity(
            @RequestParam @NotBlank String color,
            @RequestParam @NotBlank String operation,
            @RequestParam @Min(0) @Max(100) int cottonPart ) {
        int count = socksService.countQuantity(color, operation, cottonPart);
        return new ResponseEntity<>(Integer.toString(count), HttpStatus.OK);
    }
}
