package com.example.bootcamp.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SocksController {

    @PostMapping("/api/socks/income")
    public void income() {

    }

    @PostMapping("/api/socks/outcome")
    public void outcome() {

    }

    @GetMapping("/api/socks")
    public void getSocks() {

    }
}
