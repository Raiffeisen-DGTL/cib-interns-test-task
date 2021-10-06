package com.example.testtask.controllers;

import com.example.testtask.store.repositories.SocksRepository;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.transaction.Transactional;
import java.util.Optional;

@RequiredArgsConstructor
@FieldDefaults(makeFinal = true)
@Transactional
@RestController
public class ProjectController {

    SocksRepository socksRepository;
    private static final String SOCKS_INCOME = "/api/socks/income";
    private static final String SOCKS_OUTCOME = "/api/socks/outcome";
    private static final String GET_SOCKS = "/api/socks";


    @GetMapping(GET_SOCKS)
    public String getSocks(@RequestParam("color") Optional<String> socksColor,
                           @RequestParam("operation") Optional<String> operation,
                           @RequestParam("cottonPart") Optional<Double> socksCotton) {
        var filteredStream = socksCotton.map(socksRepository::streamAllByCottonPart);
        return "1";

    }
}
