package ru.raiffeisen.socks.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.raiffeisen.socks.dto.SocksDto;
import ru.raiffeisen.socks.dto.SocksRequestDto;
import ru.raiffeisen.socks.entity.Color;
import ru.raiffeisen.socks.entity.Socks;
import ru.raiffeisen.socks.enums.Operation;
import ru.raiffeisen.socks.service.SocksService;

import javax.validation.Valid;
import java.util.Collections;

@RestController
@RequestMapping("/api/socks")
public class SocksController {

    private final SocksService socksService;

    public SocksController(SocksService socksService) {
        this.socksService = socksService;
    }

    @PostMapping("/income")
    public void income(@Valid @RequestBody SocksDto socksDto){
        socksService.income(new Socks(1L, socksDto.getCottonPart(), socksDto.getQuantity(), new Color(1L, socksDto.getColor(), Collections.emptySet())));
    }

    @PostMapping("/outcome")
    public void outcome(@Valid @RequestBody SocksDto socksDto){
        socksService.outcome(new Socks(1L, socksDto.getCottonPart(), socksDto.getQuantity(), new Color(1L, socksDto.getColor(), Collections.emptySet())));
    }

    @GetMapping
    public Long socks(@Valid @RequestBody SocksRequestDto socksRequestDto){
        System.out.println(socksRequestDto.getOperation());
        return 0L;
    }

}
