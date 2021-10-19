package com.java.task.controller;

import com.java.task.request.SocksByParamsRequest;
import com.java.task.request.SocksRequest;
import com.java.task.response.SocksResponse;
import com.java.task.service.SocksService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class SocksController {
    private final SocksService socksService;

    @PostMapping("/api/socks/income")
    public ResponseEntity<SocksResponse> income(
            @Validated @ModelAttribute("socksRequest") SocksRequest socksRequest
    ) {
        return new ResponseEntity<>(socksService.incomeSocks(socksRequest), HttpStatus.OK);
    }

    @PostMapping("/api/socks/outcome")
    public ResponseEntity<SocksResponse> outcome(
            @Validated @ModelAttribute("socksRequest") SocksRequest socksRequest
    ) {
        return new ResponseEntity<>(socksService.outcomeSocks(socksRequest), HttpStatus.OK);
    }

    @GetMapping("/api/socks")
    public ResponseEntity<String> getByParams(
            @Validated @ModelAttribute("socksByParamsRequest") SocksByParamsRequest socksByParamsRequest
    ) {
        return new ResponseEntity<>(socksService.getSocksValueByParams(socksByParamsRequest).toString(), HttpStatus.OK);
    }
}
