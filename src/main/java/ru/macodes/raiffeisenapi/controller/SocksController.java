package ru.macodes.raiffeisenapi.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.macodes.raiffeisenapi.data.dto.GetSocksDTO;
import ru.macodes.raiffeisenapi.data.dto.SocksDto;
import ru.macodes.raiffeisenapi.service.SocksService;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/socks")
@RequiredArgsConstructor
public class SocksController {

    private final SocksService socksService;

    @PostMapping("/income")
    public ResponseEntity<?> income(@Valid @RequestBody SocksDto socks) {
        socksService.addSocks(socks);

        return ResponseEntity.ok().build();
    }

    @PostMapping("/outcome")
    public ResponseEntity<?> outcome(@Valid @RequestBody SocksDto socks) {
        socksService.deleteSocks(socks);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity<?> getSocks(@Valid GetSocksDTO getSocksDTO) {
        long count = socksService.getSocksCount(getSocksDTO);

        return ResponseEntity.ok(count);
    }


}
