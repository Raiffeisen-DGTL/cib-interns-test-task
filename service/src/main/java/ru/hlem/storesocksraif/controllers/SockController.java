package ru.hlem.storesocksraif.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.hlem.storesocksraif.entity.Sock;
import ru.hlem.storesocksraif.service.SockService;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/socks")
public class SockController {
    private final SockService sockService;

    @Autowired
    public SockController(SockService sockService) {
        this.sockService = sockService;
    }

    @GetMapping()
    public String show(@RequestParam(name = "color") String color,
                       @RequestParam(name = "operation") String operation,
                       @RequestParam(name = "cottonPart") Integer cottonPart) {
        String countSocks = sockService.show(color, operation, cottonPart);
        return countSocks;
    }

    @PostMapping("/income")
    public void income(@Valid @RequestBody Sock sock) {
        sockService.income(sock);
    }

    @PostMapping("/outcome")
    public void outcome(@Valid @RequestBody Sock sock) {
        sockService.outcome(sock);
    }
}