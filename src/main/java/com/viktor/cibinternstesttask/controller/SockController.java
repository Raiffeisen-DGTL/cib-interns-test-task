package com.viktor.cibinternstesttask.controller;

import com.viktor.cibinternstesttask.dto.SockDto;
import com.viktor.cibinternstesttask.dto.SockParamsDto;
import com.viktor.cibinternstesttask.service.SockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/socks")
public class SockController {

    @Autowired
    private SockService sockService;

    @PostMapping("/income")
    public ResponseEntity<String> registerIncome(@RequestBody @Valid SockDto sockDto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().body("Something wrong with parameters");
        }

        sockService.update(sockDto, SockService.Action.INCOME);

        return ResponseEntity.ok().body("Data was updated");
    }

    @PostMapping("/outcome")
    public ResponseEntity<String> registerOutcome(@RequestBody @Valid SockDto sockDto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().body("Something wrong with parameters");
        }

        sockService.update(sockDto, SockService.Action.OUTCOME);

        return ResponseEntity.ok().body("Data was updated");
    }

    @GetMapping
    public ResponseEntity<String> getSocks(@Valid SockParamsDto sockParamsDto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().body("Something wrong with parameters");
        }

        Long sumOfQuantities;
        try {
            sumOfQuantities = sockService.getSumOfNecessarySocks(sockParamsDto);
        } catch (IllegalArgumentException exception) {
            return ResponseEntity.badRequest().body(exception.getMessage());
        }

        String response = String.valueOf(sumOfQuantities);

        return ResponseEntity.ok().body(response);
    }
}
