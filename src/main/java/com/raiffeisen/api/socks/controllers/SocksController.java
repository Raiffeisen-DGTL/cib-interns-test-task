package com.raiffeisen.api.socks.controllers;

import com.raiffeisen.api.socks.entities.Sock;
import com.raiffeisen.api.socks.exceptions.BadArgumentException;
import com.raiffeisen.api.socks.exceptions.NoSuchSocksFoundException;
import com.raiffeisen.api.socks.services.SockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/socks")
public class SocksController {

    @Autowired
    private SockService sockService;

    @GetMapping()
    public ResponseEntity <String> getSocksQuantity(
            @RequestParam String color,
            @RequestParam String operation,
            @RequestParam Integer cottonPart)
    {
        if (color == null || operation == null || cottonPart == null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        try {
            String quantity = sockService.getSocksQuantityByParams(color,cottonPart,operation).toString();
            return new ResponseEntity<>(quantity, HttpStatus.OK);
        }
        catch (IllegalArgumentException illegalArgumentException){
            throw new BadArgumentException(operation);
        }
    }

    @PostMapping("/income")
    public ResponseEntity <String> socksIncome(@RequestBody Sock sock){
        String incomeResult = sockService.increaseSocksQuantityByParams(sock);
        switch (incomeResult){
            case ("new"): return new ResponseEntity<>
                        ("Not find combination of color and cottonPart, created new", HttpStatus.CREATED);
            default: return new ResponseEntity<>
                    ("Added " + sock.getQuantity() + " socks. " + incomeResult + " socks in store.", HttpStatus.OK);
        }
    }

    @PostMapping("/outcome")
    public ResponseEntity <String> socksOutcome(@RequestBody Sock sock){
       try{
           String outComeResult = sockService.decreaseSocksQuantityByParams(sock);
           return new ResponseEntity<>("Removed " + sock.getQuantity() + " socks. " + outComeResult + " left.", HttpStatus.OK);
       } catch (NoSuchSocksFoundException e) {
           return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
       }
    }
}
