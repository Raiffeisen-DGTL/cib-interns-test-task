package com.example.demo.controller;

import com.example.demo.entity.Socks;
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
    public ResponseEntity<String> socksIncome(@Valid @RequestBody Socks socks) throws NotFoundException {
        //check if entity with those params already in DB
        if (socksService.isAlreadyExist(socks)) {
            //update qnt
            socksService.updateQuantityIncome(socks);
        } else {
            //add socks and set qnt
            socksService.addSocks(socks);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/socks/outcome")
    public ResponseEntity<String> socksOutcome(@Valid @RequestBody Socks socks) throws NotFoundException {
        //check if entity with those params already in DB
        if (!socksService.isAlreadyExist(socks)) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        //check if there is enough socks in DB
        if (socksService.isEnoughQuantity(socks)) {
            socksService.updateQuantityOutcome(socks);
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>("don't have such amount of socks", HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/socks")
    public ResponseEntity<String> socksGetQuantity(@RequestParam String color, @RequestParam String operation, @RequestParam int cottonPart){
        return socksService.countSocksWithParams(color, cottonPart, operation);
    }
}