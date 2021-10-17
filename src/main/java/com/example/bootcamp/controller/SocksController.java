package com.example.bootcamp.controller;

import com.example.bootcamp.dto.SocksDto;
import com.example.bootcamp.service.GetSocksService;
import com.example.bootcamp.service.IncomeService;
import com.example.bootcamp.service.OutcomeService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class SocksController {

    private final IncomeService incomeService;
    private final OutcomeService outcomeService;
    private final GetSocksService getSocksService;

    public SocksController(IncomeService incomeService, OutcomeService outcomeService,
                           GetSocksService getSocksService) {
        this.incomeService = incomeService;
        this.outcomeService = outcomeService;
        this.getSocksService = getSocksService;
    }

    @PostMapping("/socks/income")
    public void income(@RequestBody List<SocksDto> socksDtoList) {
        incomeService.income(socksDtoList);
    }

    @PostMapping("/socks/outcome")
    public void outcome(@RequestBody List<SocksDto> socksDtoList) {
        outcomeService.outcome(socksDtoList);
    }

    @GetMapping("/socks")
    public int getSocks(@RequestParam("color") java.lang.String color, @RequestParam("operation") String operation,
                        @RequestParam("cottonPart") int cottonPart) {
        return getSocksService.getSocks(color, operation, cottonPart);
    }
}
