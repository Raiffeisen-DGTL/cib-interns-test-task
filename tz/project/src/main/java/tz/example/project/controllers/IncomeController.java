package tz.example.project.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import tz.example.project.models.Socks;
import tz.example.project.services.IncomeService;

@Controller
public class IncomeController {

    @Autowired
    private final IncomeService incomeService;

    @Autowired
    public IncomeController(IncomeService incomeService) {
        this.incomeService = incomeService;
    }

    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/income")
    public void income(@RequestBody Socks socks) {
        incomeService.income(socks);
    }
}
