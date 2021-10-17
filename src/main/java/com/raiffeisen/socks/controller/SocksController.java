package com.raiffeisen.socks.controller;

import com.raiffeisen.socks.dto.SockDto;
import com.raiffeisen.socks.service.SocksService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/api/socks")
public class SocksController {
    private final SocksService socksService;

    @Autowired
    public SocksController(SocksService socksService) {
        this.socksService = socksService;
    }

    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/income")
    public void incomeSock(@RequestBody SockDto newSocks) {
        socksService.registerSocks(newSocks);
    }

    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/outcome")
    public void outcomeSock(@RequestBody SockDto socksDto) {
        socksService.outcomeSocks(socksDto);
    }

    @GetMapping()
    public SockDto getSocks(@RequestParam("color") String color,
                            @RequestParam("operation") String operation,
                            @RequestParam("cottonPart") Integer cottonPart) {
        return socksService.getSocksByParams(color, operation, cottonPart);
    }
}
