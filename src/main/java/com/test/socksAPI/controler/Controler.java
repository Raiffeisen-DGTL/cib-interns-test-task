package com.test.socksAPI.controler;

import com.test.socksAPI.accessingdata.Socks;
import com.test.socksAPI.repository.SocksRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/socks")
public class Controler {
    @Autowired
    private SocksRepository socksRepository;


    @PostMapping(path="/income",  consumes="application/json") // добавить обязвтельные параметры
    public
    ResponseEntity<Void> income (@RequestBody Socks newSocks) {

        if(newSocks.getCottonPart() <= 0 || newSocks.getCottonPart() > 100 || newSocks.getQuantity() < 0 || newSocks.getColor() == null)
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "request parameters are missing or have an incorrect format");

        socksRepository.updateQuantity(newSocks.getColor(), newSocks.getCottonPart(), newSocks.getQuantity());

        return  ResponseEntity.ok().build();
    }

    @PostMapping(path="/outcome",  consumes="application/json")
    public ResponseEntity<Void> outcome (@RequestBody Socks newSocks) {

        if(newSocks.getCottonPart() <= 0 || newSocks.getCottonPart() > 100 || newSocks.getQuantity() < 0 || newSocks.getColor() == null)
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "request parameters are missing or have an incorrect format");

        List<Socks> list = socksRepository.findByColorAndCottonPart(newSocks.getColor(), newSocks.getCottonPart());
        if(list.size() != 1)
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "no such socks found in stock");
        int newQuantity = list.get(0).getQuantity() - newSocks.getQuantity();
        if(newQuantity > 0) {
            list.get(0).setQuantity(newQuantity);
            socksRepository.save(list.get(0));
        }
        else if(newQuantity == 0) {
            socksRepository.delete(list.get(0));
        }
        else
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "the outcome of socks is too high. Max quantity " + list.get(0).getQuantity());
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public @ResponseBody
    long countSocks (@RequestParam String color, @RequestParam String operation, @RequestParam String cottonPart) {


        try {
            if(operation.equals("moreThan"))
                return socksRepository.countSocksMoreThan(color,  Byte.parseByte(cottonPart));
            if(operation.equals("lessThan"))
                return socksRepository.countSocksLessThan(color,  Byte.parseByte(cottonPart));
            if(operation.equals("equal"))
                return socksRepository.countSocksEqual(color,  Byte.parseByte(cottonPart));
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "incorrect value operation");
        }
        catch (NumberFormatException e)
        {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "incorrect format cottonPart", e);
        }

    }



    @GetMapping(path="/all")
    public @ResponseBody Iterable<Socks> getAll() {
        return socksRepository.findAll();
    }





}
