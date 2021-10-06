package com.example.testtask.controllers;

import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.RestController;

import javax.transaction.Transactional;

@RequiredArgsConstructor
@FieldDefaults(makeFinal = true)
@Transactional
@RestController
public class ProjectController {
    private static final String SOCKS_INCOME = "/api/socks/income";
    private static final String SOCKS_OUTCOME = "/api/socks/outcome";
    private static final String GET_SOCKS = "/api/socks";
}
