package ru.project.raiffeisenbank.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ru.project.raiffeisenbank.dto.*;
import ru.project.raiffeisenbank.service.SocksService;

import javax.validation.Valid;


@RestController
@RequestMapping(value = "/api/sosks", produces = MediaType.APPLICATION_JSON_VALUE)
public class SocksController {
    private SocksService mainService;

    @Autowired
    public SocksController(SocksService mainService) {
        this.mainService = mainService;
    }

    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/income")
    public SocksResponse<IncomeResponse> income (@RequestBody @Valid IncomeRequest request) {
        return new SocksResponse<>(mainService.income(request));
    }

    @PostMapping("/outcome")
    public SocksResponse<OutcomeResponse> outcome (@RequestBody @Valid OutcomeRequest request) {
        return new SocksResponse<>(mainService.outcome(request));
    }

    @GetMapping()
    public Long outputMethod (
            @RequestParam @Valid String color,
            @RequestParam @Valid String operation,
            @RequestParam @Valid int cottonPart
    ) {
        return new SocksResponse<>(mainService.getSocks(color, operation, cottonPart)).getResult();
    }
}
