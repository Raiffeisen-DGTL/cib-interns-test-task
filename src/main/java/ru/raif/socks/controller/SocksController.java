package ru.raif.socks.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.raif.socks.request.Comparison;
import ru.raif.socks.request.SocksIncomeOutcomeRq;
import ru.raif.socks.service.SocksService;

import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@RestController
@Validated
public class SocksController {

    private final SocksService socksService;

    @Autowired
    public SocksController(SocksService socksService) {
        this.socksService = socksService;
    }

    @PostMapping("/api/socks/income")
    @ResponseStatus(HttpStatus.OK)
    public String income(@Valid @RequestBody SocksIncomeOutcomeRq request) {
        socksService.income(request);
        return "удалось добавить приход";
    }

    @PostMapping("/api/socks/outcome")
    @ResponseStatus(HttpStatus.OK)
    public String outcome(@Valid @RequestBody SocksIncomeOutcomeRq request) {
        socksService.outcome(request);
        return "удалось отразить расход";
    }

    @GetMapping("/api/socks")
    @ResponseStatus(HttpStatus.OK)
    public String search(@RequestParam @NotBlank String color,
                         @RequestParam @NotNull Comparison operation,
                         @RequestParam @Min(0) @Max(100) Integer cottonPart) {
        return socksService.search(color, operation, cottonPart).toString();
    }
}

