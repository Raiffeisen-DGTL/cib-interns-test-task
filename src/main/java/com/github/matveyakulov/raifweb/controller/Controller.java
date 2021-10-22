package com.github.matveyakulov.raifweb.controller;

import com.github.matveyakulov.raifweb.databind.OperationConverter;
import com.github.matveyakulov.raifweb.entity.Sock;
import com.github.matveyakulov.raifweb.enums.Operation;
import com.github.matveyakulov.raifweb.service.SockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/socks")
public class Controller {

    @Autowired
    private SockService sockService;

    @PostMapping(   "/income")
    public void income(@RequestParam("color") String color, @RequestParam("cottonPart") String cottonPart,
                               @RequestParam("quantity") String quantity) {

        sockService.income(new Sock(color, Integer.parseInt(cottonPart), Integer.parseInt(quantity)));

    }

    @PostMapping("/outcome")
    public void outcome(@RequestParam("color") String color, @RequestParam("cottonPart") String cottonPart,
                        @RequestParam("quantity") String quantity) {

        sockService.outcome(new Sock(color, Integer.parseInt(cottonPart), Integer.parseInt(quantity)));

    }

    @GetMapping("")
    public String getForParametr(@RequestParam("color") String color, @RequestParam("operation") String urlOperation,
                                @RequestParam("cottonPart") String cotton) {

        urlOperation = urlOperation.toUpperCase();
        int cottonPart = Integer.parseInt(cotton);

        Operation operation = OperationConverter.convert(urlOperation);

        if(operation != Operation.UNDEFIEND) {
            int count = sockService.getAll(color, operation, cottonPart);
            return " " + count;
        }else {
            return "0";
        }

    }

}
