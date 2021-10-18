package com.vadim01er.taskraiffeisenbank.controller;

import com.vadim01er.taskraiffeisenbank.dto.SocksDto;
import com.vadim01er.taskraiffeisenbank.entity.Operation;
import com.vadim01er.taskraiffeisenbank.service.SocksService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.util.List;

@RestController
@RequestMapping("/api/socks")
@RequiredArgsConstructor
@Validated
public class ApiController {

    private final SocksService<SocksDto> service;

    @GetMapping
    public ResponseEntity<List<SocksDto>> findAll(
            @RequestParam(value = "color", required = false) String color,
            @RequestParam(value = "operation", required = false) String operation,
            @Valid @Min(0) @Max(100)@RequestParam(value = "cottonPart", required = false) Short cottonPart
    ) {
        return ResponseEntity.ok(service.findByColorAndCottonPart(color, cottonPart, Operation.fromString(operation)));
    }

    @PostMapping("/income")
    public ResponseEntity<?> income(@Valid @RequestBody SocksDto socksDto) {
        return ResponseEntity.ok(service.income(socksDto));
    }

    @PostMapping("/outcome")
    public ResponseEntity<?> outcome(@Valid @RequestBody SocksDto socksDto) {
        return ResponseEntity.ok(service.delete(socksDto));
    }
}
