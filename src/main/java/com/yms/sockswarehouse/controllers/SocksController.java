package com.yms.sockswarehouse.controllers;

import com.yms.sockswarehouse.models.Socks;
import com.yms.sockswarehouse.repositories.SocksRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.ConstraintViolationException;
import java.util.List;

@RestController
@Validated
public class SocksController {

    @Autowired
    SocksRepository socksRepository;

    @RequestMapping(value = "/api/socks/income", method = RequestMethod.POST)
    ResponseEntity<String> income (@RequestParam(value = "color") String color, @RequestParam(value = "cottonPart") int cottonpart, @RequestParam(value = "quantity") Long quantity) {
        String statusMessage;
        if(socksRepository.findBycottonpartAndColor(cottonpart, color) != null) {
            socksRepository.save(new Socks(socksRepository.findBycottonpartAndColor(cottonpart, color).getId(),color, cottonpart, (quantity + socksRepository.findBycottonpartAndColor(cottonpart, color).getQuantity())));
            statusMessage = "Socks quantity increased successfully.";
            }
        else {
            socksRepository.save(new Socks(color, cottonpart, quantity));
            statusMessage = "Socks added successfully.";
        }
        return ResponseEntity.ok(statusMessage);
    }

    @RequestMapping(value = "/api/socks/outcome", method = RequestMethod.POST)
    ResponseEntity<String> outcome (@RequestParam(value = "color") String color, @RequestParam(value = "cottonPart") int cottonpart, @RequestParam(value = "quantity") Long quantity) {
        ResponseEntity<String> respEnt;
        if(socksRepository.findBycottonpartAndColor(cottonpart, color) != null) {  //check availability of socks to outcome
            if(socksRepository.findBycottonpartAndColor(cottonpart, color).getQuantity() >=quantity) { //check that stock quantity is not higher than outcome quantity
                socksRepository.save(new Socks(socksRepository.findBycottonpartAndColor(cottonpart, color).getId(), color, cottonpart, (socksRepository.findBycottonpartAndColor(cottonpart, color).getQuantity() - quantity)));
                respEnt = ResponseEntity.ok("Socks quantity decreased successfully.");
            }
            else respEnt = new ResponseEntity<>("Outcome quantity is higher than stock quantity", HttpStatus.BAD_REQUEST);

        }
        else {
            respEnt = new ResponseEntity<>("Socks not found.", HttpStatus.BAD_REQUEST);
        }
        return respEnt;
    }

    @RequestMapping(value = "/api/socks", method = RequestMethod.GET)
    public ResponseEntity<String> counter (@RequestParam(value = "color") String color, @RequestParam String operation, @RequestParam(value = "cottonPart") int cottonpart) {
        Long fondQuantity = 0L;
        switch (operation) {
            case "moreThan":
                List<Socks> lsock = socksRepository.findAllBycottonpartGreaterThanAndColor(cottonpart, color);
                for(int i = 0; i<lsock.size(); i++) {
                    fondQuantity+=lsock.get(i).getQuantity();
            }
                break;
            case "lessThan":
                List<Socks> lsock2 = socksRepository.findAllBycottonpartLessThanAndColor(cottonpart, color);
                for(int i = 0; i<lsock2.size(); i++) {
                    fondQuantity+=lsock2.get(i).getQuantity();
                }
                break;
            case "equal":
                List<Socks> lsock3 = socksRepository.findAllBycottonpartAndColor(cottonpart, color);
                for(int i = 0; i<lsock3.size(); i++) {
                    fondQuantity+=lsock3.get(i).getQuantity();
                }
                break;
        }
        return ResponseEntity.ok(fondQuantity.toString());
    }

    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<String> handleConstraintViolationException(ConstraintViolationException e) {
        return new ResponseEntity<>("Arguments validation error: " + e.getMessage(), HttpStatus.BAD_REQUEST);
    }

}


