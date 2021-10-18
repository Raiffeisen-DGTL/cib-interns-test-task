package com.herokuapp.cibinternship.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Pattern;
import javax.validation.Valid;

import com.herokuapp.cibinternship.service.SocksService;
import com.herokuapp.cibinternship.model.Socks;


@RestController
@RequestMapping("/api/socks")
public class SocksController {

    private final SocksService socksService;

    @Autowired
    public SocksController(SocksService socksService) {
        this.socksService = socksService;
    }

    @PostMapping("/income")
    public ResponseEntity registerSocksIncome(@Valid @RequestBody Socks socks) {
        socksService.registerSocksIncome(socks);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/outcome")
    public ResponseEntity registerSocksOutcome(@Valid @RequestBody Socks socks) {
        socksService.registerSocksOutcome(socks);
        return ResponseEntity.ok().build();
    }

    @GetMapping("")
    public long getSocksQuantity(@RequestParam("color") String color,
                                 @RequestParam("operation")
                                 @Pattern(regexp = "lessthan|morethan|equal", flags = Pattern.Flag.CASE_INSENSITIVE)
                                         String operation,
                                 @RequestParam("cottonPart") int cottonPart) {
        return socksService.getSocksQuantity(color, operation, cottonPart);
    }

}
