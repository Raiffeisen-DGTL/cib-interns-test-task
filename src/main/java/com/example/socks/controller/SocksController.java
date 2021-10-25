package com.example.socks.controller;

import com.example.socks.exception.OutOfBoundsException;
import com.example.socks.model.SockModel;
import com.example.socks.service.SocksService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/socks")
public class SocksController {

    private final SocksService socksService;

    @PostMapping("/income")
    public ResponseEntity<Integer> addSocks(@RequestBody SockModel sockModel) {
        int quantity = socksService.addSocks(sockModel);
        return new ResponseEntity<>(quantity, HttpStatus.OK);
    }

    @PostMapping("/outcome")
    public ResponseEntity<Integer> subSocks(@RequestBody SockModel sockModel) {
        int quantity = socksService.subSocks(sockModel);
        return new ResponseEntity<>(quantity, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<Integer> getSocks(@RequestParam String color,
                                            @RequestParam String operation,
                                            @RequestParam int cottonPart) {
        if (cottonPart < 0 || cottonPart > 100)
            throw new OutOfBoundsException("cottonPart must be between 0 and 100");

        int quantity = socksService.getSocks(color, operation, cottonPart);
        return new ResponseEntity<>(quantity, HttpStatus.OK);
    }
}
