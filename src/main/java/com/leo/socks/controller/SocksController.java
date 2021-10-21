package com.leo.socks.controller;

import com.leo.socks.dto.GettingByParamsDto;
import com.leo.socks.dto.IncomeOutcomeDto;
import com.leo.socks.service.SocksService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@Validated
@RequiredArgsConstructor
@RequestMapping("/api/socks")
public class SocksController {

    private final SocksService socksService;

    @RequestMapping("/income")
    public HttpStatus addSocks(@Valid IncomeOutcomeDto socks) {
        socksService.add(socks);
        return HttpStatus.OK;
    }

    @RequestMapping("/outcome")
    public HttpStatus takeSocks(@Valid IncomeOutcomeDto socks) {
        socksService.take(socks);
        return HttpStatus.OK;
    }

    @RequestMapping
    public ResponseEntity<?> getSocksQuantity( @Valid GettingByParamsDto params) {
        return new ResponseEntity<>(socksService.getQuantity(params), HttpStatus.OK);
    }
}
