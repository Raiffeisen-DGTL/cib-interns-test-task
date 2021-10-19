package com.kozlov.springjpa.spring_data_jpa.controller;


import com.kozlov.springjpa.spring_data_jpa.Operations;
import com.kozlov.springjpa.spring_data_jpa.entity.Socks;
import com.kozlov.springjpa.spring_data_jpa.exception.IncorrectRequestParamException;
import com.kozlov.springjpa.spring_data_jpa.exception.NegativeIncomeOrOutcomeException;
import com.kozlov.springjpa.spring_data_jpa.exception.NoRequiredParamException;
import com.kozlov.springjpa.spring_data_jpa.service.SocksService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value="/api")
public class RESTControllers {

    private SocksService socksService;

    @Autowired
    public RESTControllers(SocksService socksService) {
        this.socksService = socksService;
    }

    @GetMapping(value = "/socks")
    public String getSocksQuantity(@RequestParam(value = "color") String color, @RequestParam(value = "operation")
            String operation, @RequestParam(value = "cottonPart") int cottonPart) {

        if(cottonPart < 0 || !Operations.contains(operation)) {
            throw new IncorrectRequestParamException();
        } else {
            return String.valueOf(socksService.getSocksQuantity(color, operation, cottonPart));
        }
    }

    @PostMapping(value = "/socks/income")
    public HttpStatus incomeSocks(@RequestBody Socks socks) {

        if(socks.getColor() != null && socks.getCottonPart() != null && socks.getQuantity() != null) {
            if (socks.getQuantity() < 0) {
                throw new NegativeIncomeOrOutcomeException();
            } else {
                socksService.setIncomeSocks(socks);

                return HttpStatus.OK;
            }
        } else {
            throw new NoRequiredParamException();
        }
    }

    @PostMapping(value = "/socks/outcome")
    public HttpStatus outcomeSocks(@RequestBody Socks socks) {
        if (socks.getColor() != null && socks.getCottonPart() != null && socks.getQuantity() != null) {
            if (socks.getQuantity() < 0) {
                throw new NegativeIncomeOrOutcomeException();
            } else {
                socksService.setOutcomeSocks(socks);

                return HttpStatus.OK;
            }
        } else {
            throw new NoRequiredParamException();
        }
    }

}

