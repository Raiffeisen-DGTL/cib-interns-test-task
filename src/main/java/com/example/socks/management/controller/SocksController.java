package com.example.socks.management.controller;

import com.example.socks.management.dto.Color;
import com.example.socks.management.dto.Operation;
import com.example.socks.management.dto.SocksDto;
import com.example.socks.management.service.SocksService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Locale;

@Validated
@RestController
public class SocksController {

    private final SocksService socksService;

    public SocksController(SocksService socksService) {
        this.socksService = socksService;
    }


    @PostMapping(path = "/api/socks/income")
    public ResponseEntity<Void> addSocks( @Valid @RequestBody SocksDto request) {
        socksService.addSocks(request);
        return ResponseEntity.ok().build();
    }

    @PostMapping(path = "/api/socks/outcome")
    public ResponseEntity<Void> removeSocks( @Valid @RequestBody SocksDto request) {
        socksService.removeSocks(request);
        return ResponseEntity.ok().build();
    }

    @GetMapping(path = "/api/socks")
    public ResponseEntity<Integer> getNumberTypeOfSocks( @RequestParam("color") String color, @RequestParam("operation") String operation, @RequestParam("cottonPart") Integer cottonPart ) {
        if (isValidRequest(color, operation, cottonPart)) {
            return new ResponseEntity<>(socksService.getNumberTypeOfSocks(Color.fromString(color), Operation.fromString(operation), cottonPart), HttpStatus.OK);
        } else return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    private static boolean isValidRequest(String color, String operation, Integer cottonPart) {
        if(cottonPart <= 100 && cottonPart >= 0
           && Color.isLegalString(color)
           && Operation.isLegalString(operation)) {
            return true;
        } else return false;
    }


}

