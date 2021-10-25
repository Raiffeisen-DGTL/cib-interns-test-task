package com.example.testsocks.controller;

import com.example.testsocks.service.SocksService;
import com.example.testsocks.model.Socks;
import com.example.testsocks.model.SocksPK;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
public class SocksController {

    @Autowired
    private SocksService socksService;


    @GetMapping("/api/socks")
    public ResponseEntity<String> getSocks(@RequestParam String color, @RequestParam String operation, @RequestParam int cottonPart) {
        SocksPK socksPK = new SocksPK(color, cottonPart);
        return new ResponseEntity<>(
                String.valueOf(socksService.countSocks(socksPK, operation)),
                HttpStatus.OK);
    }
    //http://localhost:8080/api/socks?color=red&operation=moreThan&cottonPart=50

    @PostMapping("/api/socks/income")
    public ResponseEntity<?> incomeSocks(@Valid @RequestBody  Socks socks) {
        socksService.income(socks);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/api/socks/outcome")
    public ResponseEntity<?> outcomeSocks(@Valid @RequestBody  Socks socks) {
        if (socksService.outcome(socks)) {
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.valueOf(400));
        }
    }
}
