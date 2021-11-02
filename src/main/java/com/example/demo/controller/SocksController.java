package com.example.demo.controller;

import com.example.demo.entity.Socks;
import com.example.demo.exception.SocksQuantityException;
import com.example.demo.service.SocksService;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api")
public class SocksController {

    @Autowired
    SocksService socksService;

    @PostMapping("/socks/income")
    public ResponseEntity<String> socksIncome(@Valid @RequestBody Socks socks) {

            socksService.updateQuantityIncome(socks);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/socks/outcome")
    public ResponseEntity<String> socksOutcome(@Valid @RequestBody Socks socks) {

        try {
            socksService.updateQuantityOutcome(socks);
        } catch (NotFoundException notFoundException) {
            return new ResponseEntity<>(notFoundException.getMessage(), HttpStatus.NOT_FOUND);
        } catch (SocksQuantityException socksQuantityException) {
            return new ResponseEntity<>(socksQuantityException.getMessage(), HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/socks")
    public ResponseEntity<String> socksGetQuantity(@RequestParam String color, @RequestParam String operation, @RequestParam int cottonPart){
        return socksService.countSocksWithParams(color, cottonPart, operation);
    }
}