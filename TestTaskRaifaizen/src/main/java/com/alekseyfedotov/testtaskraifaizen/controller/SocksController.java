package com.alekseyfedotov.testtaskraifaizen.controller;

import com.alekseyfedotov.testtaskraifaizen.entity.Socks;
import com.alekseyfedotov.testtaskraifaizen.service.Operation;
import com.alekseyfedotov.testtaskraifaizen.service.SocksService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/socks")
public class SocksController {

    @Autowired
    private SocksService socksService;

    @PostMapping("/income")
    public ResponseEntity addSocks(@RequestBody Socks socks){
        return socksService.addSocks(socks);
    }

    @PostMapping("/outcome")
    public ResponseEntity removeSocks(@RequestBody Socks socks){
        return socksService.removeSocks(socks);
    }

    @GetMapping("/all")
    public List<Socks> getAll(){
        return socksService.getAll();
    }

    @GetMapping()
    public Integer getCountWithParams(@RequestParam("color") String color,
                                      @RequestParam("operation") String operation,
                                      @RequestParam("cottonPart") Integer cottonPart){
        return socksService.getCountOfSocks(color, operation, cottonPart);
    }

}
