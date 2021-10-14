package ru.sillyseal.raiffeisentask.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.sillyseal.raiffeisentask.service.SockService;
import ru.sillyseal.raiffeisentask.model.Sock;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Pattern;
import java.util.List;

@RestController
@RequestMapping("/api/socks")
@Validated
public class SockController {
    private final SockService sockService;

    @Autowired
    public SockController(SockService sockService) {
        this.sockService = sockService;
    }

    @PostMapping("/income")
    public void registerIncome(@RequestParam("color") String color, @RequestParam("cottonPart") @Min(0) @Max(100) int cotton_part,
                               @RequestParam("quantity") @Min(1) int quantity){
        sockService.incomeSocks(color,cotton_part,quantity);
    }

    @PostMapping("/outcome")
    public void registerOutcome(@RequestParam("color") String color, @RequestParam("cottonPart") @Min(0) @Max(100) int cotton_part,
                               @RequestParam("quantity") @Min(1) int quantity){
        sockService.outcomeSocks(color,cotton_part,quantity);
    }

    @GetMapping("")
    public int countSocks(@RequestParam("color") String color,
                          @RequestParam("operation")
                          @Pattern(regexp = "morethan|equal|lessthan", flags = Pattern.Flag.CASE_INSENSITIVE) String operation,
                          @RequestParam("cottonPart") @Min(0) @Max(100) int cotton_part){
        return sockService.countSocks(color,operation,cotton_part);
    }

    @GetMapping("/all")
    public List<Sock> allSocks() {
        return sockService.getSocks();
    }
}
