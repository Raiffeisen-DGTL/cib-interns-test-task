package com.raif.socks.controller;

import com.raif.socks.dto.SocksDto;
import com.raif.socks.service.Operation;
import com.raif.socks.service.SocksService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/socks")
public class SocksController {

    private final SocksService socksService;

    @GetMapping
    public int getSocksQuantity(@RequestParam("color") String color,
                                @RequestParam("operation") Operation operation,
                                @RequestParam("cottonPart") int cottonPart) {
        checkCottonPart(cottonPart);
        return socksService.find(color, operation, cottonPart);
    }

    @PostMapping("/income")
    public void registerIncome(@RequestBody SocksDto socksDto) {
        checkCottonPart(socksDto.getCottonPart());
        socksService.income(socksDto);
    }

    @PostMapping("/outcome")
    public void registerOutcome(@RequestBody SocksDto socksDto) {
        checkCottonPart(socksDto.getCottonPart());
        socksService.outcome(socksDto);
    }

    @GetMapping("/enable")
    public String isEnable() {
        return "ok";
    }

    private void checkCottonPart(int cottonPart) {
        if (cottonPart < 0 || cottonPart > 100) {
            throw new IllegalArgumentException("Cotton part can be 0...100");
        }
    }

}
