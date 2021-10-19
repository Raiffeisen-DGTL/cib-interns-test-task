package ru.khromov.raiftask.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.khromov.raiftask.DAO.Sock;
import ru.khromov.raiftask.service.RaifService;

import java.util.List;
import java.util.NoSuchElementException;


@RestController
public class RaifController {

    private final RaifService raifService;

    @Autowired
    public RaifController(RaifService raifService) {
        this.raifService = raifService;
    }

    @GetMapping("/api/socks/all")
    public List<Sock> showBySocks() {
        return raifService.showBySocks();
    }

    @GetMapping("/api/socks")
    public ResponseEntity<Integer> getSocks(@RequestParam String color, @RequestParam String operation, @RequestParam int cottonPart) {
        return new ResponseEntity<>((raifService.getSocks(color, operation, cottonPart)), HttpStatus.CREATED);

    }

    @PostMapping("/api/socks/income")
    public ResponseEntity<String> income(@RequestBody Sock sock) {
        try {
            raifService.income(sock);
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
            raifService.outcome(sock);
            return new ResponseEntity<>("Ok", HttpStatus.CREATED);
        } catch (IllegalArgumentException | NoSuchElementException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
