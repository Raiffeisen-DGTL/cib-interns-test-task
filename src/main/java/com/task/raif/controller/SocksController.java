package com.task.raif.controller;
import com.task.raif.model.Socks;
import com.task.raif.service.SocksService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.util.Map;

@RestController
@Validated
public class SocksController {
    private final SocksService socksService;

    @Autowired
    public SocksController(SocksService socksService) {
        this.socksService = socksService;
    }

    @PostMapping("/api/socks/income")
    public ResponseEntity<?> socksIncome(@Valid @RequestBody Socks socks) {
        socksService.income(socks);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/api/socks/outcome")
    public ResponseEntity<?> socksOutcome(@Valid @RequestBody Socks socks) {
        socksService.outcome(socks);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/api/socks")
    public ResponseEntity<?> getSocksController(
            @RequestParam @NotBlank String color,
            @RequestParam @Pattern(regexp = "moreThan|lessThan|equal") String operation,
            @RequestParam @Min(0) @Max(100) int cottonPart) {
        int quantity = socksService.getSocksByParams(color, operation, cottonPart);
        return new ResponseEntity<>(Map.of("Quantity", quantity), HttpStatus.OK);
    }
}
