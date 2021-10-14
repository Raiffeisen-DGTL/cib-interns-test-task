package com.raiffeisen.bootcamps.astakhovartem.socksapi.controller;


import com.raiffeisen.bootcamps.astakhovartem.socksapi.entity.Socks;
import com.raiffeisen.bootcamps.astakhovartem.socksapi.service.SocksService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

@Validated
@RestController
@RequestMapping("/api")
public class MyRestController {

    private final SocksService socksService;

    public MyRestController(SocksService socksService) {
        this.socksService = socksService;
    }

    @PostMapping("/socks/income")
    public ResponseEntity<String> socksIncome(@Valid @RequestBody Socks socks) {
        socksService.increaseSocks(socks);
        return new ResponseEntity<>("accepted socks", HttpStatus.OK);
    }

    @ResponseStatus(value = HttpStatus.OK)
    @PostMapping("/socks/outcome")
    public ResponseEntity<String> socksOutcome(@RequestBody Socks socks) {
        socksService.decreaseSocks(socks);
        return new ResponseEntity<>("sent socks", HttpStatus.OK);
    }

    @GetMapping("/socks")
    public ResponseEntity<Integer> showQuantitySocks(@RequestParam("color") @NotBlank String color,
                                                     @RequestParam("operation") @NotBlank String operation,
                                                     @RequestParam("cottonPart") @Min(0) @Max(100) int cottonPart) {
        return new ResponseEntity<>(socksService.getQuantity(color, operation, cottonPart), HttpStatus.OK);
    }
}