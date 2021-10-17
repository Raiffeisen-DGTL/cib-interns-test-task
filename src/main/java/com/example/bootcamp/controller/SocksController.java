package com.example.bootcamp.controller;

import com.example.bootcamp.dto.Operation;
import com.example.bootcamp.dto.SocksDto;
import com.example.bootcamp.service.IncomeService;
import com.example.bootcamp.service.OutcomeService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class SocksController {

    private final IncomeService incomeService;
    private final OutcomeService outcomeService;

    public SocksController(IncomeService incomeService, OutcomeService outcomeService) {
        this.incomeService = incomeService;
        this.outcomeService = outcomeService;
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
    public int getSocks(@RequestParam("color") String color, @RequestParam("operation") Operation operation,
                        @RequestParam("cottonPart") short cottonPart) {
        return 0;
    }
}
