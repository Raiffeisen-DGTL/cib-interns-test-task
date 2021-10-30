package ru.vasiliev.socks.controller;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import ru.vasiliev.socks.repository.Socks;
import ru.vasiliev.socks.services.SocksServices;

import javax.validation.Valid;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Slf4j
@RequiredArgsConstructor
@RestController
public class SocksController {

    private final SocksServices socksServices;

    @GetMapping(value = "/socks", produces = APPLICATION_JSON_VALUE)
    public List<Socks> getSocks(@RequestParam("color") String color,
                             @RequestParam("cottonPath") Integer cottonPath,
                             @RequestParam("operation") String operation){
        log.info("Get socks");
        return socksServices.getSocks(color,cottonPath,operation);
    }

    @PostMapping(value = "/socks/income", produces = APPLICATION_JSON_VALUE)
    public Socks income(@Valid @RequestBody Socks socks){
        log.info("Income socks");
        return socksServices.income(socks);
    }



    @PostMapping(value = "/socks/outcome", produces = APPLICATION_JSON_VALUE)
    public void outcome(@Valid @RequestBody Socks socks) {
        log.info("Outcome socks");
        socksServices.outcome(socks);
    }



}
