package ru.yakovlev.socks.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.yakovlev.socks.model.Sock;
import ru.yakovlev.socks.service.SockService;

import java.util.NoSuchElementException;

@RestController
public class SockController {
    private final SockService sockService;

    @Autowired
    public SockController(SockService sockService) {
        this.sockService = sockService;
    }

    @GetMapping("/api/socks")
    public ResponseEntity<Integer> getSocks(@RequestParam String color, @RequestParam String operation, @RequestParam int cottonPart) {
        return new ResponseEntity<>((sockService.getSocks(color, operation, cottonPart)), HttpStatus.CREATED);

    }

    @PostMapping("/api/socks/income")
    public ResponseEntity<String> income(@RequestBody Sock sock) {
        try {
            sockService.income(sock);
            return new ResponseEntity<>("Ok", HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @PostMapping("/api/socks/outcome")
    public ResponseEntity<String> outcome(@RequestBody Sock sock) {
        try {
            sockService.outcome(sock);
            return new ResponseEntity<>("Ok", HttpStatus.CREATED);
        } catch (IllegalArgumentException | NoSuchElementException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
