package ru.ikuzin.DGTLTask.Socks.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.ikuzin.DGTLTask.Socks.dto.SocksInfo;
import ru.ikuzin.DGTLTask.Socks.service.SocksService;

import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

@RestController
@RequestMapping("/api/socks")
@RequiredArgsConstructor
public class SocksController {
    private final SocksService service;

    @PostMapping("/income")
    public void income(@Valid @RequestBody SocksInfo incomeInfo) {
        service.takeIncome(incomeInfo);
    }

    @PostMapping("/outcome")
    public void outcome(@Valid @RequestBody SocksInfo outcomeInfo) {
        service.takeOutcome(outcomeInfo);
    }

    @GetMapping
    public SocksInfo getStockInfo(@RequestParam String color,
                              @RequestParam @Max(100) @Min(1) Integer cottonPart,
                              @RequestParam String operation) {
        return service.getStockInfo(color, cottonPart, operation);
    }
}
