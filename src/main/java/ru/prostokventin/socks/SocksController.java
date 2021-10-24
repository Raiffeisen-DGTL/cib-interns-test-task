package ru.prostokventin.socks;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
public class SocksController {

    @Autowired
    private SocksService socksService;

    @PostMapping("/api/socks/income")
    public ResponseEntity socksIncome(@RequestBody @Valid Socks socks) {
        socksService.income(socks);
        return new ResponseEntity(HttpStatus.OK);
    }

    @PostMapping("/api/socks/outcome")
    public ResponseEntity socksOutcome(@RequestBody @Valid Socks socks) {
        socksService.outcome(socks);
        return new ResponseEntity(HttpStatus.OK);
    }

    @GetMapping("/api/socks")
    public int getSocksCount(@RequestParam String color,
                             @RequestParam Operation operation,
                             @RequestParam int cottonPart) {

        return socksService.getSocksCount(color, operation, cottonPart);
    }

}
