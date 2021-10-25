package ru.morboui.raiff.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.morboui.raiff.entity.Socks;
import ru.morboui.raiff.enums.Operations;
import ru.morboui.raiff.service.SocksService;

@RequestMapping("api/socks")
@RestController
public class SocksController {

    private final SocksService socksService;

    @Autowired
    public SocksController(SocksService socksService) {
        this.socksService = socksService;
    }

    @GetMapping
    public ResponseEntity<String> getByColorAndCottonPart(
            @RequestParam(value = "color") final String color,
            @RequestParam(value = "operation") final Operations operation,
            @RequestParam(value = "cottonPart") final Long cottonPart) {
        return new ResponseEntity<>("Quantity of socks with such parameters: " +
                socksService.getByColorAndCottonPart(color, operation, cottonPart).getQuantity(),
                HttpStatus.OK);


    }

    @PostMapping(value = "income")
    public ResponseEntity<String> addNewSocks(@RequestBody Socks socks) {
        socksService.addNewSocks(socks);
        return new ResponseEntity<>("Added socks: " + socks.getQuantity() +
                " with color " + socks.getColor() +
                " and CottonPart: " + socks.getCottonPart(), HttpStatus.OK);
    }

    @PostMapping(value = "outcome")
    public ResponseEntity<String> reduceSocks(@RequestBody Socks socks) {
        socksService.reduceSocks(socks);
        return new ResponseEntity<>("Reduced socks: " + socks.getQuantity() +
                " with color " + socks.getColor() + " and CottonPart: " + socks.getCottonPart(),
                HttpStatus.OK);

    }
}
