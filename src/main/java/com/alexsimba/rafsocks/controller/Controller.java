package com.alexsimba.rafsocks.controller;

import com.alexsimba.rafsocks.Service.SocksService;
import com.alexsimba.rafsocks.dto.SocksForGet;
import com.alexsimba.rafsocks.dto.SocksForPost;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
public class Controller {
    @Autowired
    private SocksService socksService;

    @GetMapping("/api/socks")
    public String getSocksSum(@ModelAttribute("SocksForGet") @Valid SocksForGet socksForGet) {
        return socksService.getSocksSum(socksForGet);
    }

    @PostMapping("/api/socks/income")
    public String create(@RequestBody @Valid SocksForPost socksForPost) {
        return socksService.create(socksForPost);
    }

    @PostMapping("/api/socks/outcome")
    @Transactional
    public String delete(@RequestBody @Valid SocksForPost socksForPost) {
        return socksService.delete(socksForPost);
    }
}