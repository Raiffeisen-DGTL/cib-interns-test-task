package com.viktor.cibinternstesttask.controller;

import com.viktor.cibinternstesttask.dto.SockDto;
import com.viktor.cibinternstesttask.dto.SockParamsDto;
import com.viktor.cibinternstesttask.exception.WrongParameterException;
import com.viktor.cibinternstesttask.service.SockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/socks")
public class SockController {

    @Autowired
    private SockService sockService;

    @PostMapping("/income")
    public void registerIncome(@RequestBody @Valid SockDto sockDto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new WrongParameterException("Something wrong with parameters");
        }

        sockService.update(sockDto, SockService.Action.INCOME);
    }

    @PostMapping("/outcome")
    public void registerOutcome(@RequestBody @Valid SockDto sockDto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new WrongParameterException("Something wrong with parameters");
        }

        sockService.update(sockDto, SockService.Action.OUTCOME);
    }

    @GetMapping
    public String getSocks(@Valid SockParamsDto sockParamsDto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new WrongParameterException("Something wrong with parameters");
        }

        Long sumOfQuantities = sockService.getSumOfNecessarySocks(sockParamsDto);

        return String.valueOf(sumOfQuantities);
    }
}
