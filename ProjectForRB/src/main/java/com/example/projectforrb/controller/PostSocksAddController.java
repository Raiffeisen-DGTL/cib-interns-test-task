package com.example.projectforrb.controller;

import com.example.projectforrb.service.SocksService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Slf4j
@RequiredArgsConstructor
@Controller
public class PostSocksAddController {

    private final SocksService service;


    @PostMapping("/api/socks/income")
    @ResponseBody
    public ResponseEntity PostSocks(@RequestParam String color, Integer quantity, Integer cottonPart) {


        if (!isCorrectParameters(color, quantity, cottonPart)) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        try {
            if (service.findByColorAndCottonPart(color, cottonPart).isPresent()) {
                service.findByColorAndCottonPart(color, cottonPart).ifPresent(socksEntity -> {
                    Integer oldQuantity = socksEntity.getQuantity();
                    socksEntity.setQuantity(oldQuantity + quantity);
                });
                return new ResponseEntity<>(HttpStatus.OK);
            }

            // При условии, что таких носков нет
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    public boolean isCorrectParameters(String color, Integer quantity, Integer cottonPart) {
        return color != null && quantity != null && cottonPart != null && quantity > 0;
    }
}
