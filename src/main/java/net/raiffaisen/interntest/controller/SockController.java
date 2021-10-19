package net.raiffaisen.interntest.controller;

import java.util.ArrayList;
import java.util.List;

import net.raiffaisen.interntest.model.Sock;
import net.raiffaisen.interntest.repository.SockRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public Sock incomeSocks(
            @RequestParam String color,
            @RequestParam Integer cottonPart,
            @RequestParam Integer quantity,
            @RequestBody Sock sock){
        return sockRepository.save(sock);
    }


}
