package com.example.socks.controller;

import com.example.socks.model.Socks;
import com.example.socks.service.SocksService;
import com.example.socks.utils.Operation;
import com.example.socks.utils.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class SocksController {

    @Autowired
    private SocksService socksService;

    @RequestMapping(value="/socks", method = RequestMethod.GET)
    public ResponseEntity<Integer> getSocks(
                           @RequestParam("color") String color,
                           @RequestParam("operation") String operation,
                           @RequestParam("cottonPart") int cottonPart ) {
        try {
            Operation operation1 = Validator.getEnumByValue(operation);
            if (!Validator.validateCottonPart(cottonPart) || operation1 == null) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
            int result = socksService.getSocks(color, operation1, cottonPart);
            return new ResponseEntity<>(result, HttpStatus.OK);
        } catch (IllegalArgumentException ex) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (Exception ex) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping(value="/socks/income")
    public ResponseEntity<Socks> addSocks(@RequestBody Socks socks) {
        try {
            if(socks == null) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
            socksService.addSocks(socks);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping(value="/socks/outcome")
    public ResponseEntity<Socks> deleteSocks(@RequestBody Socks socks) {
        try {
            if(socks == null) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
            socksService.deleteSocks(socks);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
