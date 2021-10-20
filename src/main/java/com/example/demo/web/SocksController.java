package com.example.demo.web;

import com.example.demo.model.Sock;
import com.example.demo.repository.SockRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping(path = "api/socks")
public class SocksController {

    @Autowired
    private SockRepository repository;

    @GetMapping("")
    public ResponseEntity<?> getSocks(
            @RequestParam String color,
            @RequestParam String operation,
            @RequestParam int cottonPart) {
        try {
            int sum;
            switch (operation) {
                case ("equal"):
                    sum = repository.sumQuantityWhenColorEqualsAndCottonPartEquals(color, cottonPart);
                    break;
                case ("moreThan"):
                    sum = repository.sumQuantityWhenColorEqualsAndCottonPartMoreThan(color, cottonPart);
                    break;
                case ("lessThan"):
                    sum = repository.sumQuantityWhenColorEqualsAndCottonPartLessThan(color, cottonPart);
                    break;
                default:
                    return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }

            return new ResponseEntity<>(sum, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
//        return "Greetings from SocksController!" + color + operation + cottonPart;
    }

    @PostMapping("/income")
    public ResponseEntity income(
            @RequestParam String color,
            @RequestParam int quantity,
            @RequestParam int cottonPart) {
        try {
            if (cottonPart <= 100 && cottonPart >= 0 && quantity > 0) {
                List<Sock> socks = repository.findByColorAndCottonPart(color, cottonPart);
                Sock sock;
                if (socks.size() != 0) {
                    sock = socks.get(0);
                    sock.setQuantity(socks.get(0).getQuantity() + quantity);
                } else {
                    sock = new Sock(color, cottonPart, quantity);
                }
                this.repository.save(sock);
                return new ResponseEntity<>(HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/outcome")
    public ResponseEntity outcome(
            @RequestParam String color,
            @RequestParam int quantity,
            @RequestParam int cottonPart) {
        try {
            if (cottonPart <= 100 && cottonPart >= 0 && quantity > 0) {
                List<Sock> socks = repository.findByColorAndCottonPart(color, cottonPart);
                if (socks.size() != 0 && socks.get(0).getQuantity() >= quantity) {
                    Sock sock = socks.get(0);
                    sock.setQuantity(socks.get(0).getQuantity() - quantity);
                    this.repository.save(sock);
                } else {
                    return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
                }
                return new ResponseEntity<>(HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


}
