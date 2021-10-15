package com.example.projectforrb.controller;

import com.example.projectforrb.service.SocksService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@RequiredArgsConstructor
@Controller
public class GetSocksController {

    private final SocksService service;
    private final static String equal = "equal";
    private final static String moreThan = "moreThan";
    private final static String lessThan = "lessThan";

    @GetMapping("/api/socks")
    @ResponseBody
    public ResponseEntity getSocks(@RequestParam String color, String operation, Integer cottonPart) {

        Integer response;

        if (!isCorrectParameters(color, operation, cottonPart)) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        try {
            switch (operation) {
                case (equal):
                    response = service.countAllByColorEqualsAndCottonPartEquals(color, cottonPart);
                    return new ResponseEntity<>(response, HttpStatus.OK);
                case (moreThan):
                    response = service.countAllByColorEqualsAndCottonPartIsGreaterThan(color, cottonPart);
                    return new ResponseEntity<>(response, HttpStatus.OK);
                case (lessThan):
                    response = service.countAllByColorEqualsAndCottonPartIsLessThan(color, cottonPart);
                    return new ResponseEntity<>(response, HttpStatus.OK);
                default:
                    return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public boolean isCorrectParameters(String color, String operation, Integer cottonPart) {
        return color != null && operation != null && cottonPart != null;
    }
}
