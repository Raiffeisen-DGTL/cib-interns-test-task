package com.ziborov.raifproject.controller;

import com.ziborov.raifproject.dto.SocksAccountingRequest;
import com.ziborov.raifproject.service.SocksWarehouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/api/socks")
public class SocksController {

    private final SocksWarehouseService socksWarehouseService;

    @Autowired
    public SocksController(SocksWarehouseService socksWarehouseService) {
        this.socksWarehouseService = socksWarehouseService;
    }

    @PostMapping(value = "/income")
    @ResponseStatus(HttpStatus.OK)
    public void socksIncome(@Valid @RequestBody SocksAccountingRequest incomeRequest) {
        socksWarehouseService.socksIncome(incomeRequest);
    }

    @PostMapping(value = "/outcome")
    @ResponseStatus(HttpStatus.OK)
    public void socksOutcome(@Valid @RequestBody SocksAccountingRequest outcomeRequest) {
        socksWarehouseService.socksOutcome(outcomeRequest);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<String> getSocksQuantity(@RequestParam(value = "color") String color,
                                                   @RequestParam(value = "operation") String operation,
                                                   @RequestParam(value = "cottonPart") Integer cottonPart) {
        Long socksQuantity = socksWarehouseService.getSocksQuantity(color, operation, cottonPart);

        return new ResponseEntity<>(socksQuantity.toString(), HttpStatus.OK);
    }

}