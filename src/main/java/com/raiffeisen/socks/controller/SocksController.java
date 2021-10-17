package com.raiffeisen.socks.controller;

import com.raiffeisen.socks.dto.SockDto;
import com.raiffeisen.socks.service.SocksService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/socks")
public class SocksController {
    private final SocksService socksServiceImpl;

    @Autowired
    public SocksController(SocksService socksServiceImpl) {
        this.socksServiceImpl = socksServiceImpl;
    }

    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/income")
    public void incomeSock(@RequestBody SockDto newSocks) {
        socksServiceImpl.registerSocks(newSocks);
    }

    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/outcome")
    public void outcomeSock(@RequestBody SockDto socksDto) {
        socksServiceImpl.outcomeSocks(socksDto);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping()
    public SockDto getSocks(@RequestParam("color") String color,
                            @RequestParam("operation") String operation,
                            @RequestParam("cottonPart") Integer cottonPart) {
        return socksServiceImpl.getSocksByParams(color, operation, cottonPart);
    }
}
