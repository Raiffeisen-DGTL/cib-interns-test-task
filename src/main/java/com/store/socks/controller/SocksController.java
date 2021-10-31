package com.store.socks.controller;

import com.store.socks.dto.IncomeOutcomeDtoRequest;
import com.store.socks.service.Operation;
import com.store.socks.service.SocksService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("/api/socks")
public class SocksController {
    @Autowired
    private final SocksService socksService;

    public SocksController(SocksService socksService) {
        this.socksService = socksService;
    }

    @GetMapping
    public ResponseEntity<Integer> getSocksNumberGivenParams(@RequestParam String color, @RequestParam Operation operation, @RequestParam int cottonPart){
        Optional<Integer> result =  socksService.getSocksNumberGivenParams(color, operation, cottonPart);
        return new ResponseEntity<>(result.orElse(0), HttpStatus.OK);
    }

    @PostMapping("/income")
    public ResponseEntity<Boolean> registerSocksIncome(@RequestBody IncomeOutcomeDtoRequest dtoRequest){
        boolean result = socksService.registerSocksIncome(dtoRequest.getColor(), dtoRequest.getCottonPart(), dtoRequest.getQuantity());
        HttpStatus status;
        if(result){
            status = HttpStatus.OK;
        } else {
            status = HttpStatus.BAD_REQUEST;
        }
        return new ResponseEntity<>(result, status);
    }

    @PostMapping("/outcome")
    public ResponseEntity<Boolean> registerSocksOutcome(@RequestBody IncomeOutcomeDtoRequest dtoRequest){
        boolean result = socksService.registerSocksOutcome(dtoRequest.getColor(), dtoRequest.getCottonPart(), dtoRequest.getQuantity());
        HttpStatus status;
        if(result){
            status = HttpStatus.OK;
        } else {
            status = HttpStatus.BAD_REQUEST;
        }
        return new ResponseEntity<>(result, status);
    }
}
