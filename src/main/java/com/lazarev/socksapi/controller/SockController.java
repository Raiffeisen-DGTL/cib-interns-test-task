package com.lazarev.socksapi.controller;

import com.lazarev.socksapi.dto.SockDto;
import com.lazarev.socksapi.service.SockService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@RestController
@RequestMapping("/api/socks")
@AllArgsConstructor
@Slf4j
public class SockController {

    private final SockService sockService;

    @PostMapping("/income")
    public ResponseEntity<?> addSocksToStoreHouse(@Valid @RequestBody SockDto sockDto){
        if(log.isInfoEnabled()){
            log.info("Adding socks to Database (addition)");
        }
        sockService.addSocks(sockDto);
        return ResponseEntity
                .status(HttpStatus.OK)
                .build();
    }

    @PostMapping("/outcome")
    public ResponseEntity<?> subtractSocksToStoreHouse(@Valid @RequestBody SockDto sockDto){
        if(log.isInfoEnabled()){
            log.info("Taking socks from Database (subtraction)");
        }
        sockService.subSocks(sockDto);
        return ResponseEntity
                .status(HttpStatus.OK)
                .build();
    }


    @GetMapping
    public ResponseEntity<String> getCountRequestMatchingSocks(
            @RequestParam("color") String color,
            @RequestParam("operation") String operation,
            @RequestParam("cottonPart") Integer cottonPart
    ){
        if(log.isInfoEnabled()){
            log.info("Summing quantity of request matching socks");
        }
        Integer quantitySum =  sockService.countRequestMatchingSocks(color.toLowerCase(), operation, cottonPart);
        quantitySum = quantitySum == null ? 0 : quantitySum;
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(Integer.toString(quantitySum));
    }
}
