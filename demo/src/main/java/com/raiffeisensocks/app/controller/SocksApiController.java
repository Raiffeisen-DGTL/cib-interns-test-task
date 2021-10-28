package com.raiffeisensocks.app.controller;

import com.raiffeisensocks.app.dto.SocksDto;
import com.raiffeisensocks.app.service.SocksServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Pattern;


@RestController
public class SocksApiController {


    private final SocksServiceImpl service;

    @Autowired
    public SocksApiController(SocksServiceImpl service) {
        this.service = service;
    }

    @GetMapping("/api/socks")
    public ResponseEntity<?> getSocks(@RequestParam String color,
                                      @RequestParam @Pattern(regexp = "moreThan|lessThan|equal") String operation,
                                      @RequestParam Integer cottonPart) {
        return new ResponseEntity<>(service.getAllSocksByParams(color, operation, cottonPart), HttpStatus.OK);
    }

    @PostMapping("/api/socks/income")
    public ResponseEntity<?> income(@Valid @RequestBody SocksDto socks) {
        service.addIncome(socks);
        return new ResponseEntity<>(HttpStatus.OK);
    }


    @PostMapping("/api/socks/outcome")
    public ResponseEntity<?> outcome(@Valid @RequestBody SocksDto socks) {
        service.addOutcome(socks);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
