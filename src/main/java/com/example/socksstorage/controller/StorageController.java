package com.example.socksstorage.controller;

import com.example.socksstorage.model.Socks;
import com.example.socksstorage.service.StorageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/socks")
@RequiredArgsConstructor
public class StorageController {

    private final StorageService storageService;

    @PostMapping(value = "/income")
    @ResponseStatus(HttpStatus.OK)
    public void create(@RequestParam String color,
                        @RequestParam int cottonPart,
                        @RequestParam int quantity) {
        storageService.create(color, cottonPart, quantity);
    }

    @PostMapping(value = "/outcome")
    @ResponseStatus(HttpStatus.OK)
    public void delete(@RequestParam String color,
                        @RequestParam int cottonPart,
                        @RequestParam int quantity) {
        boolean deleted = storageService.delete(color, Socks.Operation.equal, cottonPart, quantity);
        if (!deleted) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Not enough socks");
        }
    }

    @GetMapping
    public ResponseEntity<?> read(@RequestParam(defaultValue = "no") String color,
                                    @RequestParam Socks.Operation operation,
                                    @RequestParam int cottonPart) {
        List<Socks> socks = storageService.readAll(color, operation, cottonPart);
        return !socks.isEmpty()
                ? new ResponseEntity<>(socks.size(), HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
}
