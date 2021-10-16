package com.example.cibinternstesttask.ui.controllers;

import com.example.cibinternstesttask.io.entities.SockEntity;
import com.example.cibinternstesttask.services.SockService;
import com.example.cibinternstesttask.ui.model.requests.SocksDetailsRequest;
import com.example.cibinternstesttask.ui.model.responses.SocksOperations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/socks")
public class SockController {

    @Autowired
    SockService sockService;

    @PostMapping(path = "/income", consumes = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public SockEntity incomeSocks(@Valid @RequestBody SocksDetailsRequest socksDetailsRequest) {
        return sockService.incomeSocks(socksDetailsRequest);
    }

    @PostMapping(path = "/outcome", consumes = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public SockEntity outcomeSocks(@Valid @RequestBody SocksDetailsRequest socksDetailsRequest) {
        return sockService.outcomeSocks(socksDetailsRequest);
    }

    @GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<?> getSocksQuantity(@RequestParam(value = "color") String color,
                                              @RequestParam(value = "operation") String operation,
                                              @RequestParam(value = "cottonPart") int cottonPart) {

        return ResponseEntity.ok(sockService.getSocksQuantity(color, SocksOperations.forOperation(operation), cottonPart));
    }
}
