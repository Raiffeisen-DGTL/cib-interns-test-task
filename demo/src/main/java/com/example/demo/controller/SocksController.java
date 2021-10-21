package com.example.demo.controller;

import com.example.demo.service.SocksService;
import org.springframework.http.HttpStatus;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;


import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import java.util.Arrays;


@Validated
@RequestMapping("/api/socks")
@RestController()
public class SocksController {
    private SocksService service;

    public SocksController(SocksService service) {
        this.service = service;
    }

    @PostMapping("/income")
    public void income(@RequestParam @NotBlank String color,
                       @RequestParam @Min(0) @Max(100) int cottonPart,
                       @RequestParam @Min(1) int quantity) {

        service.postIncome(color, cottonPart, quantity);
    }

    @PostMapping("/outcome")
    public void outcome(@RequestParam String color,
                        @RequestParam @Min(0) @Max(100) int cottonPart,
                        @RequestParam @Min(1) int quantity) {

        service.postOutcome(color, cottonPart, quantity);
    }

    @GetMapping("/")
    public int getAmountOfSocks(@RequestParam @NotBlank String color,
                                @RequestParam String operation,
                                @RequestParam @Min(0) @Max(100) int cottonPart) {
        if (!Arrays.asList("moreThan", "lessThan", "equal").contains(operation)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Operation is not correct");
        }
        return service.getAmountOfSocks(color, operation, cottonPart);
    }
}



