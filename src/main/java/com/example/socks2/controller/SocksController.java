package com.example.socks2.controller;

import com.example.socks2.dto.SocksFilterParams;
import com.example.socks2.entity.SocksEntity;
import com.example.socks2.exception.InvalidArgumentException;
import com.example.socks2.exception.SocksNotFoundException;
import com.example.socks2.service.SocksService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class SocksController {

    private final SocksService socksService;

    @Autowired
    public SocksController(SocksService socksService) {
        this.socksService = socksService;
    }

    @PostMapping("/socks/income")
    public ResponseEntity<?> createSocks(@RequestBody SocksEntity sock) {
        try {
            socksService.arrivalSocks(sock);
        } catch (SocksNotFoundException e) {
            return ResponseEntity.notFound().build();
        } catch (InvalidArgumentException e) {
            return ResponseEntity.badRequest().body("Произошла ошибка в добавлении товара");
        }

        return ResponseEntity.ok("Товар успешно добавлен");
    }

    @PostMapping("/socks/outcome")
    public ResponseEntity<?> deleteSocks(@RequestBody SocksEntity sock) {
        try {
            socksService.departureSocks(sock);
        } catch (SocksNotFoundException e) {
            return ResponseEntity.notFound().build();
        } catch (InvalidArgumentException e) {
            return ResponseEntity.badRequest().body("Произошла ошибка в отправлении товара");
        }

        return ResponseEntity.ok("Товар успешно добавлен");
    }


    @GetMapping("/socks")
    public ResponseEntity<?> getSocks(
            @RequestParam String color,
            @RequestParam Long cottonPart,
            @RequestParam String operation
    ) {
        final var filterParams = new SocksFilterParams(color, operation, cottonPart);

        try {
            return ResponseEntity.ok(socksService.getSocks(filterParams));
        } catch (InvalidArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}





