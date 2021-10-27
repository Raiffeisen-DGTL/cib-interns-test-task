package ru.raiffeisen.socks.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.raiffeisen.socks.dto.SocksChangeDto;
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
    public void income(@Valid @RequestBody SocksChangeDto socksChangeDto) {
        socksService.income(socksChangeDto.getColor(), socksChangeDto.getCottonPart(), socksChangeDto.getQuantity());
    }

    @PostMapping("/outcome")
    public void outcome(@Valid @RequestBody SocksChangeDto socksChangeDto) {
        socksService.outcome(socksChangeDto.getColor(), socksChangeDto.getCottonPart(), socksChangeDto.getQuantity());
    }

    @GetMapping
    public Long socks(@Valid SocksRequestDto socksRequestDto) {
        return socksService.socks(socksRequestDto.getColor(), socksRequestDto.getOperation(), socksRequestDto.getCottonPart());
    }

}
