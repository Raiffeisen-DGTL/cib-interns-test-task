package com.example.raif.controller;

import com.example.raif.entity.SocksEntity;
import com.example.raif.exception.IncorrectParametersException;
import com.example.raif.exception.SocksNotFoundException;
import com.example.raif.exception.SocksQuantityOutOfRangeException;
import com.example.raif.service.SocksService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/socks")
public class SocksController {
    @Autowired
    private SocksService socksService;

    @PostMapping("/income")
    public ResponseEntity<String> addSocks(@Valid @RequestBody SocksEntity socks) {
        return new ResponseEntity<>(socksService.addSocks(socks), HttpStatus.OK);
    }

    @PostMapping("/outcome")
    public ResponseEntity<String> deleteSocks(@Valid @RequestBody SocksEntity socks) {
        try {
            return new ResponseEntity<>(socksService.deleteSocks(socks), HttpStatus.OK);
        } catch (SocksQuantityOutOfRangeException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        } catch (SocksNotFoundException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping
    public ResponseEntity<List<SocksEntity>> getSocks(@RequestParam(name = "color") String color,
                                   @RequestParam(name = "operation") String operation,
                                   @RequestParam(name = "cottonPart") int cottonPart) {
        try {
            List<SocksEntity> socks = socksService.getSocks(color, operation, cottonPart);
            if (!socks.isEmpty()) {
                return new ResponseEntity<>(socks, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (IncorrectParametersException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

}
