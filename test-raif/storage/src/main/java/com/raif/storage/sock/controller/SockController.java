package com.raif.storage.sock.controller;

import com.raif.storage.sock.model.SockDto;
import com.raif.storage.sock.service.SockService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/socks")
@AllArgsConstructor
public class SockController {

    private final SockService sockService;

    @PostMapping("/income")
    void createSockIncome(@RequestBody SockDto sock) {
        sockService.createSockIncome(sock);
    }

    @PostMapping("/outcome")
    @ResponseStatus(HttpStatus.OK)
    void createSockOutcome(@RequestBody SockDto sock) {
        sockService.createSockOutcome(sock);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    long countSocks(@RequestParam(name = "color") String color,
                    @RequestParam(name = "operation") String operation,
                    @RequestParam(name = "cottonPart") int cottonPart) {

        return sockService.countSocks(color, operation, cottonPart);
    }
}
