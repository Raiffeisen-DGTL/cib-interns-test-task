package com.raiffeisendgtl.ApiSocks.controllers;

import com.raiffeisendgtl.ApiSocks.components.SocksException;
import com.raiffeisendgtl.ApiSocks.services.SocksService;
import com.raiffeisendgtl.ApiSocks.entities.Socks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class SocksController {
    @Autowired
    private SocksService socksService;

    @PostMapping("api/socks/income")
    public ResponseEntity<String> income(@RequestBody Socks request) {
        socksService.income(request);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("api/socks/outcome")
    public ResponseEntity<String> outcome(@RequestBody Socks request) {
        socksService.outcome(request);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("api/socks")
    public ResponseEntity<String> socks(@RequestParam("color") String color,
                                         @RequestParam("operation") String operation,
                                         @RequestParam("cottonPart") int cottonPart) {
        Integer result = socksService.getCountSocks(color, operation, cottonPart);
        return new ResponseEntity<>(result.toString(), HttpStatus.OK);
    }

    @ExceptionHandler(SocksException.class)
    public ResponseEntity<String> handleError(SocksException e) {
        return new ResponseEntity<>(e.getError().getStatusError());
    }

}
