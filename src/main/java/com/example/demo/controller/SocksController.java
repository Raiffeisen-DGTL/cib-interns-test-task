package com.example.demo.controller;


import com.example.demo.dto.SocksDto;
import com.example.demo.service.ISocksService;
import com.example.demo.service.SocksException;
import javassist.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

@RestController
@RequiredArgsConstructor
@Validated
public class SocksController {

    private final ISocksService iSocksService;


    @GetMapping(value = "api/socks")
    public ResponseEntity<String> getByColorAndCottonPart(@RequestParam(value = "color") final String color,
                                                          @RequestParam(value = "operation") final String operation,
                                                          @Min(value = 0) @Max(100) @RequestParam(value = "cottonPart")
                                                              final Long cottonPart) {
        return ResponseEntity.ok(iSocksService.getByColorAndCottonPart(color, operation, cottonPart).toString());


    }

    @ResponseStatus(HttpStatus.OK)
    @PostMapping(value = "api/socks/income")
    public void add(@Valid @RequestBody SocksDto socksDto) {
        iSocksService.add(socksDto);
    }

    @ResponseStatus(HttpStatus.OK)
    @PostMapping(value = "api/socks/outcome")
    public void reduce(@RequestBody SocksDto socksDto) throws SocksException, NotFoundException {
        iSocksService.reduce(socksDto);
    }


}
