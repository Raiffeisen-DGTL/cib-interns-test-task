package com.example.socksstr.REST;


import com.example.socksstr.Model.Socks;
import com.example.socksstr.Service.SocksService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Pattern;


@Slf4j
@RestController
@RequestMapping("/api/socks/")
public class SocksRestController {

    private final SocksService socksService;

    @Autowired
    public SocksRestController(SocksService socksService) {
        this.socksService = socksService;
    }

    @PostMapping("/income")
    public ResponseEntity socksIncome(@Valid @RequestBody Socks socks) {
        socksService.socksIncome(socks);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/outcome")
    public ResponseEntity socksOutcome(@Valid @RequestBody Socks socks) {
        socksService.socksOutcome(socks);
        return ResponseEntity.ok().build();
    }

    @GetMapping("")
    public long getSocksQuantity(@RequestParam("color") String color,
                                 @RequestParam("operation")
                                 @Pattern(regexp = "lessthan|morethan|equal", flags = Pattern.Flag.CASE_INSENSITIVE)
                                         String operation,
                                 @RequestParam("cottonPart") long cottonPart) {
        return socksService.getSocksQuantity(color, operation, cottonPart);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public String handle(IllegalArgumentException e) {
        log.error(e.getMessage());
        return "IllegalArgument";
    }
    @ExceptionHandler(NullPointerException.class)
    public String handle(NullPointerException e){
        log.error(e.getMessage());
        return "NullPoint";
    }

}

