package ru.raiffeisen.socks.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.raiffeisen.socks.dto.SocksDto;
import ru.raiffeisen.socks.dto.SocksRequestDto;
import ru.raiffeisen.socks.service.SocksService;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/socks")
public class SocksController {

    private final SocksService socksService;

    public SocksController(SocksService socksService) {
        this.socksService = socksService;
    }

    @PostMapping("/income")
    public void income(@Valid @RequestBody SocksDto socksDto) {
        socksService.income(socksDto.getColor(), socksDto.getCottonPart(), socksDto.getQuantity());
    }

    @PostMapping("/outcome")
    public void outcome(@Valid @RequestBody SocksDto socksDto) {
        socksService.outcome(socksDto.getColor(), socksDto.getCottonPart(), socksDto.getQuantity());
    }

    @GetMapping
    public Long socks(@Valid SocksRequestDto socksRequestDto) {
        return socksService.socks(socksRequestDto.getColor(), socksRequestDto.getOperation(), socksRequestDto.getCottonPart());
    }

}
