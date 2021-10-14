package com.rf.accountingforsocks.controller;

import com.rf.accountingforsocks.dto.SocksDto;
import com.rf.accountingforsocks.service.SocksService;
import com.rf.accountingforsocks.util.SocksOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;


@RequestMapping("/api/socks")
@RestController
@Validated
public class SocksController {
    @Autowired
    private final SocksService socksService;



    public SocksController(SocksService socksService) {
        this.socksService = socksService;
    }

    @GetMapping
    public Integer getCountOfSocks(@RequestParam(name = "color") @NotBlank String color,
                                   @RequestParam(name = "operation") @NotBlank String operation,
                                   @RequestParam(name = "cottonPart") @Min(0) @Max(100) Integer cottonPart) {
        return socksService.findQuantityOfSocksByColorOrOperationAndCottonPart(color,
                SocksOperation.valueOf(operation),
                cottonPart);
    }

    @PostMapping("/income")
    public void postIncomeSocks(@Valid @RequestBody SocksDto socksDto) {
        socksService.income(socksDto);
    }

    @PostMapping("/outcome")
    public void postOutcomeSocks(@Valid @RequestBody SocksDto socksDto) {
        socksService.findByColorAndOutcome(socksDto);
    }

}
