package com.raiffeisen.socks.controller;

import com.raiffeisen.socks.dto.SockDto;
import com.raiffeisen.socks.service.SocksService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/api/socks")
public class SocksController {
    private final SocksService socksService;

    public SocksController(SocksService socksService) {
        this.socksService = socksService;
    }

    @PostMapping(value = "/income")
    public ResponseEntity<SockDto> incomeSock(@RequestBody SockDto newSocks) {
        socksService.registerSocks(newSocks);
        return new ResponseEntity<>(newSocks, HttpStatus.OK);
    }

    @PostMapping(value = "/outcome")
    public ResponseEntity<SockDto> outcomeSock(@RequestBody SockDto socksDto) {
        socksService.outcomeSocks(socksDto);
        return new ResponseEntity<>(socksDto, HttpStatus.OK);
    }
}
