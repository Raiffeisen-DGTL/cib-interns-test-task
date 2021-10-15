package com.example.demo.controllers;

import com.example.demo.Service.SockService;
import com.example.demo.model.request.CreateSockRequest;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Api(value = "socks-docs")
@RestController
@RequestMapping("/api/socks")
public class SockController {
    @Autowired
    private SockService sockService;

    @ApiOperation(value = "Operation of adding socks")
    @PostMapping(
            value = "/income",
            consumes = APPLICATION_JSON_VALUE,
            produces = APPLICATION_JSON_VALUE
    )
    public ResponseEntity<String> socksIncome(@Valid @RequestBody CreateSockRequest request) {

        sockService.registerSockIncome(request);
        return ResponseEntity.ok("Income was successful".toString());

    }

    @ApiOperation(value = "Operation of shipping socks from the warehouse")
    @PostMapping(
            value = "/outcome",
            consumes = APPLICATION_JSON_VALUE,
            produces = APPLICATION_JSON_VALUE
    )
    public ResponseEntity<String> socksOutcome(@Valid @RequestBody CreateSockRequest request) {

        boolean result = sockService.registerSockOutcome(request);
        if (result) {
            return new ResponseEntity<>("Outcome was successful", HttpStatus.OK);

        } else {
            return new ResponseEntity<>("Not enough socks!", HttpStatus.BAD_REQUEST);
        }
    }

    @ApiOperation(value = "Operation of receiving socks")
    @GetMapping(produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<String> getSocksCount(@RequestParam("color") String color,
                                                @RequestParam("operation") String operation,
                                                @RequestParam("cottonPart") Integer cottonPart) {
        if (cottonPart < 0 || cottonPart > 100 ) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
        Long quantity = sockService.getSockQuantity(color, operation, cottonPart);
        if (quantity == -1) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
        return ResponseEntity.ok(quantity.toString());

    }
}
