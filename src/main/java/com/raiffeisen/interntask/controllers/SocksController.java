package com.raiffeisen.interntask.controllers;

import com.raiffeisen.interntask.classes.SocksOrder;
import com.raiffeisen.interntask.services.SocksService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@AllArgsConstructor
public class SocksController {

    private final SocksService service;

    @PostMapping(value = "/api/socks/income", consumes = "application/json", produces = "application/json")
    public ResponseEntity<String> incomeOrder(@RequestBody @Valid SocksOrder order, BindingResult result){
        return service.incomeOrder(order);
    }

    @PostMapping(value = "/api/socks/outcome", consumes = "application/json", produces = "application/json")
    public ResponseEntity<String> outcomeOrder(@RequestBody @Valid SocksOrder order){
        return service.outcomeOrder(order);
    }

    @GetMapping("/api/socks")
    public ResponseEntity<String> GetSocks(@RequestParam("color") String color, @RequestParam("operation") String operation,@RequestParam("cottonPart") String cottonPart){
        return service.GetOrder(color, operation, cottonPart);
    }
}
