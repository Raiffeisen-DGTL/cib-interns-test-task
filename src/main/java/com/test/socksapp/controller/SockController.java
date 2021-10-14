package com.test.socksapp.controller;

import com.test.socksapp.requestmodel.ComparisonOperation;
import com.test.socksapp.requestmodel.SockValueChangingRequest;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/socks")
public class SockController {

    @PostMapping("/income")
    @ResponseStatus(HttpStatus.OK)
    public void regIncome(@RequestBody SockValueChangingRequest request) {
    }

    @PostMapping("/outcome")
    @ResponseStatus(HttpStatus.OK)
    public void regOutcome(@RequestBody SockValueChangingRequest request) {
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public int getSockCount(@RequestParam String color,
                            @RequestParam ComparisonOperation operation,
                            @RequestParam int cottonPart) {
        return cottonPart;
    }
}
