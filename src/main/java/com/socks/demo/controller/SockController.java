package com.socks.demo.controller;

import com.socks.demo.exception.IncorrectParametersException;
import com.socks.demo.model.Sock;
import com.socks.demo.service.SockService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/socks")
public class SockController {

    private final SockService sockService;

    @PostMapping("/income")
    public ResponseEntity<String> addForm(@RequestBody Sock sock) {
        sockService.addSocks(sock);
        return ResponseEntity.ok("Удалось добавить приход. Носки добавлены: цвет - " + sock.getColor() + ", содержание хлопка(%) - "
                + sock.getCottonPart() + " , количество - " + sock.getQuantity());
    }

    @PostMapping("/outcome")
    public ResponseEntity<String> deleteForm(@RequestBody Sock sock) {
        sockService.deleteSocks(sock);
        return ResponseEntity.ok("Удалось списать со склада. Носки удалены: цвет - " + sock.getColor() + ", содержание хлопка(%) - "
                + sock.getCottonPart() + " , количество - " + sock.getQuantity());
    }

    @GetMapping()
    public ResponseEntity<Integer> getAmountSocks(@RequestParam String color, String operation, Integer cottonPart) throws IncorrectParametersException {
        return ResponseEntity.ok(sockService.amountSocks(color, operation, cottonPart));
    }

}
