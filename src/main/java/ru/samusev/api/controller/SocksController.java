package ru.samusev.api.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.samusev.api.dto.SocksRequest;
import ru.samusev.api.dto.SocksResponse;
import ru.samusev.api.sercvice.SocksService;

import static ru.samusev.api.constant.Paths.API;
import static ru.samusev.api.constant.Paths.INCOME;
import static ru.samusev.api.constant.Paths.OUTCOME;
import static ru.samusev.api.constant.Paths.SOCKS;

@RestController
@RequiredArgsConstructor
@RequestMapping(API + SOCKS)
public class SocksController {
    private final SocksService socksService;

    @GetMapping
    public ResponseEntity<String> getAllByCriterion(@RequestParam String color,
                                                    @RequestParam String operation,
                                                    @RequestParam Integer cottonPart) {
        return ResponseEntity.ok(socksService.getQuantityByCriterion(color, cottonPart ,operation).toString());
    }

    @PostMapping(INCOME)
    public ResponseEntity<SocksResponse> income(@RequestBody SocksRequest request) {
        return ResponseEntity.ok(socksService.income(request));
    }

    @PostMapping(OUTCOME)
    public ResponseEntity<SocksResponse> outcome(@RequestBody SocksRequest request) {
        return ResponseEntity.ok(socksService.outcome(request));
    }
}
