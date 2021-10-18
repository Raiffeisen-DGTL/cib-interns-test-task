package com.example.RESTServer.controller;

import com.example.RESTServer.models.Socks;
import com.example.RESTServer.repository.SocksRepository;
import org.hibernate.criterion.Example;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class SocksController {

    private final SocksRepository repository;

    SocksController(SocksRepository repository) {
        this.repository = repository;
    }

    // Example of query:
    // curl -v -H "Content-Type: application/json" -d  '{"color":"blue","cottonPart":5,"quantity":5}' http://localhost:8080/api/socks/income
    @PostMapping(value = "/api/socks/income", consumes = "application/json")
    public ResponseEntity<?> registerSocks(@RequestBody Socks socks) {
        try {
            if (socks.getColor() != null && socks.getQuantity() > 0 && socks.getCottonPart() >= 0 && socks.getCottonPart() <= 100) {
                var result = repository.save(socks);
                return new ResponseEntity<>(result, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    // Example of query:
    // curl -v -H "Content-Type: application/json" -d  '{"color":"blue","cottonPart":5,"quantity":5}' http://localhost:8080/api/socks/outcome
    @PostMapping(value = "/api/socks/outcome", consumes = "application/json")
    public ResponseEntity<?> outputSocks(@RequestBody Socks socks) {
        try {
            List<Socks> socksList = this.repository.findAll().stream()
                    .filter(x -> x.getQuantity() == socks.getQuantity())
                    .filter(x -> x.getColor().equals(socks.getColor()))
                    .filter(x -> x.getCottonPart() == socks.getCottonPart())
                    .collect(Collectors.toList());

            if (socks.getColor() != null && socksList.size() > 0) {
                repository.delete(socksList.get(0));
                return new ResponseEntity<>(HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Example of query:
    // curl http://localhost:8080/api/socks/all
    @GetMapping("/api/socks/all")
    Collection<Socks> getAllSocks() {
        return repository.findAll();
    }

    // Example of query:
    // curl "http://localhost:8080/api/socks?color=blue&operation=moreThan&cottonPart=3"
    @GetMapping(value = "/api/socks")
    public ResponseEntity<?> countNumberSocks(
            @RequestParam(value = "color", required = false) String color,
            @RequestParam(value = "operation", required = false) String operation,
            @RequestParam(value = "cottonPart", required = false) int cottonPart
    ) {
        try {
            if (color == null || operation == null || cottonPart <= 0) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
            List<Socks> socks = this.repository.findAll();
            if (operation.equals("moreThan")) {
                int numberSocks = socks.stream()
                        .filter(x -> x.getColor().equals(color))
                        .filter(x -> x.getCottonPart() > cottonPart)
                        .map(Socks::getQuantity)
                        .mapToInt(Integer::intValue)
                        .sum();
                return new ResponseEntity<>(numberSocks, HttpStatus.OK);

            }

            if (operation.equals("lessThan")) {
                int numberSocks = socks.stream()
                        .filter(x -> x.getColor().equals(color))
                        .filter(x -> x.getCottonPart() < cottonPart)
                        .map(Socks::getQuantity)
                        .mapToInt(Integer::intValue)
                        .sum();
                return new ResponseEntity<>(numberSocks, HttpStatus.OK);
            }

            if (operation.equals("equal")) {
                int numberSocks = socks.stream()
                        .filter(x -> x.getColor().equals(color))
                        .filter(x -> x.getCottonPart() < cottonPart)
                        .map(Socks::getQuantity)
                        .mapToInt(Integer::intValue)
                        .sum();
                return new ResponseEntity<>(numberSocks, HttpStatus.OK);
            }

            return new ResponseEntity<>(operation + " not recognized", HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

}
