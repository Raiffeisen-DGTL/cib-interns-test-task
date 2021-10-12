package com.davinchi.raifsocks.controllers;

import com.davinchi.raifsocks.Socks;
import com.davinchi.raifsocks.repos.SocksRepo;
import org.hibernate.criterion.Example;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.relational.core.sql.In;
import org.springframework.expression.ParseException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.print.attribute.standard.Media;
import java.util.List;
import java.util.Objects;

@Controller
public class WebController {

    @Autowired
    private SocksRepo socksRepo;


    @RequestMapping(value ="/api/socks/income", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<?> incomeSocks(@RequestBody Socks socks) throws ParseException {
        System.out.print(socks.getQuantity());
        Socks oldSocks;
        try {
            oldSocks = socksRepo.findByColorAndCottonPart(socks.getColor(), socks.getCottonPart()).get(0);
            oldSocks.setQuantity(oldSocks.getQuantity()+socks.getQuantity());
        } catch (Exception e){
            oldSocks = socks;
        }
        socksRepo.save(oldSocks);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(value ="/api/socks/outcome", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<?> outcomeSocks(@RequestBody Socks socks) throws ParseException {
        Socks oldSocks;
        try {
            oldSocks = socksRepo.findByColorAndCottonPart(socks.getColor(), socks.getCottonPart()).get(0);
        } catch (Exception e) {
            return new ResponseEntity<>("Quantity is too much", HttpStatus.BAD_REQUEST);
        }
        int temp = oldSocks.getQuantity() - socks.getQuantity();
        if (temp >= 0) {
            oldSocks.setQuantity(temp);
            socksRepo.save(oldSocks);
        } else {
            return new ResponseEntity<>("Quantity is too much", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

        @RequestMapping(value ="/api/socks", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<?> getSocksQuantity(@RequestParam String color, @RequestParam String operation, @RequestParam Integer cottonPart){
        int quantity = 0;
        List<Socks> socksList;
         switch (operation){
             case "moreThan":
                 socksList = socksRepo.findByColorAndCottonPartIsGreaterThan(color, cottonPart);
                 break;
             case "lessThan":
                 socksList = socksRepo.findByColorAndCottonPartIsLessThan(color, cottonPart);
                 break;
             case "equal":
                 socksList = socksRepo.findByColorAndCottonPartIs(color, cottonPart);
                 break;
             default:
                 throw new IllegalStateException("Unexpected value: " + operation);
         }
        for (Socks socks:socksList) {
            quantity = quantity + socks.getQuantity();
        }
        return new ResponseEntity<>(Integer.toString(quantity), HttpStatus.OK);
    }

    @ExceptionHandler(IllegalStateException.class)
        public ResponseEntity<?> badMethod(IllegalStateException illegalStateException){
            return new ResponseEntity<>("WRONG METHOD",HttpStatus.BAD_REQUEST);
        }

    @ExceptionHandler(ParseException.class)
    public ResponseEntity<?> badObject(ParseException parseException){
        return new ResponseEntity<>("WRONG DATA",HttpStatus.BAD_REQUEST);
        }
}
