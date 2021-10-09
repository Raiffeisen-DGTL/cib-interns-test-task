package com.davinchi.raifsocks.controllers;

import com.davinchi.raifsocks.Socks;
import com.davinchi.raifsocks.repos.SocksRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.relational.core.sql.In;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Objects;

@Controller
public class WebController {

    @Autowired
    private SocksRepo socksRepo;

    @PostMapping("/api/socks/income")
    public ResponseEntity incomeSocks(@RequestBody Socks socks){
        Socks oldSocks = socksRepo.findByColorAndCottonPart(socks.getColor(),socks.getCottonPart()).get(0);
        oldSocks.setQuantity(oldSocks.getQuantity()+socks.getQuantity());
        socksRepo.save(oldSocks);
        return new ResponseEntity(HttpStatus.ACCEPTED);
    }

    @PostMapping("/api/socks/outcome")
    public ResponseEntity outcomeSocks(@RequestBody Socks socks){
        Socks oldSocks = socksRepo.findByColorAndCottonPart(socks.getColor(),socks.getCottonPart()).get(0);
        oldSocks.setQuantity(oldSocks.getQuantity()-socks.getQuantity());
        socksRepo.save(oldSocks);
        return new ResponseEntity(HttpStatus.ACCEPTED);
    }

    @GetMapping("/api/socks")
    public String getSocksQuantity(@RequestParam String color, @RequestParam String operation, @RequestParam Integer cottonPart){
        List<Socks> socksList = socksRepo.findByColor(color);
        Integer quantity = 0;
        for (Socks socks: socksList)
        {
         switch (operation){
             case "moreThan":
                 if (socks.getQuantity() > cottonPart)
                 {
                     quantity += socks.getQuantity();
                 }
             case "lessThan":
                 if (socks.getQuantity() < cottonPart)
                 {
                     quantity += socks.getQuantity();
                 }
             case "equal":
                 if (Objects.equals(socks.getQuantity(), cottonPart))
                 {
                     quantity += socks.getQuantity();
                 }
         }
        }
        return quantity.toString();
    }
}
