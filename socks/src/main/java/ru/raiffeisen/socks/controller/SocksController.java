package ru.raiffeisen.socks.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.raiffeisen.socks.data.entity.Color;
import ru.raiffeisen.socks.data.entity.Socks;
import ru.raiffeisen.socks.service.SocksService;

import java.util.Collections;

@RestController
@RequestMapping("/api/socks")
public class SocksController {

    private final SocksService socksService;

    public SocksController(SocksService socksService) {
        this.socksService = socksService;
    }

    @PostMapping("/income")
    public void income(){
        System.out.println("qwe");
        socksService.income(new Socks(1L, 15, 100L, new Color(6L, "black", Collections.emptySet())));
    }

    @GetMapping("/outcome")
    public void outcome(){
        socksService.outcome(new Socks(1L, 15, 100L, new Color(6L, "black", Collections.emptySet())));
    }

}
