package net.raiffaisen.interntest.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import net.raiffaisen.interntest.model.Sock;
import net.raiffaisen.interntest.repository.SockRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

//@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/api/socks")
public class SockController {

    @Autowired
    SockRepository sockRepository;

    @GetMapping("/index")
    public List<Sock> getAllSocks(){
        return sockRepository.findAll();
    }

    @PostMapping("/income")
    public Sock incomeSocks(@Validated @RequestBody Sock sock){
        //sock.setQuantity(50);
        return sockRepository.save(sock);
    }

    @GetMapping("/")
    public Long typeCount(@RequestParam String color,@RequestParam Integer cottonPart,@RequestParam String operation){
        long resultCount = 0;
        if(Objects.equals(operation, "moreThan")){
            resultCount = sockRepository.findByCottonPartAndColorGreaterThan(cottonPart,color).stream().count();
        }
        return resultCount;
    }


}
