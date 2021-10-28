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
        if (!storageService.create(color, cottonPart, quantity)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Could not add socks");
        }
    }

    @PostMapping(value = "/outcome")
    @ResponseStatus(HttpStatus.OK)
    public void delete(@RequestParam String color,
                        @RequestParam int cottonPart,
                        @RequestParam int quantity) {
        if (!storageService.delete(color, cottonPart, quantity)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Not enough socks");
        }
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public int read(@RequestParam String color,
                    @RequestParam Socks.Operation operation,
                    @RequestParam int cottonPart) {
        int socksCount = storageService.readAll(color, operation, cottonPart);
        if (socksCount == 0) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Not enough socks");
        }
        return socksCount;
    }
}
