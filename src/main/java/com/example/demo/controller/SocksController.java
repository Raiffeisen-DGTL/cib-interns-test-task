package com.example.demo.controller;

import com.example.demo.model.Socks;
import com.example.demo.model.SocksDB;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/socks")
public class SocksController {

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, value ="/income")
    @ResponseBody
    void income(@RequestBody Socks socks){
        Socks sock=SocksDB.selectByColorAndCotton(socks.getColor(), socks.getCottonPart());
        if(sock==null) SocksDB.insert(socks);
        else{ sock.setQuantity(sock.getQuantity()+socks.getQuantity());
            SocksDB.update(sock);
        }
    }
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, value ="/outcome")
    @ResponseBody
    void outcome(@RequestBody Socks socks){

        Socks sock=SocksDB.selectByColorAndCotton(socks.getColor(), socks.getCottonPart());
        if(sock!=null && sock.getQuantity()>=socks.getQuantity()) { sock.setQuantity(sock.getQuantity()-socks.getQuantity());
            SocksDB.update(sock);
        }
    }
    @GetMapping()
    int getQuantity(@RequestParam String color, @RequestParam String operation, @RequestParam int cottonPart){
        int quantity=SocksDB.selectByColorAndCotton(color, cottonPart, operation);
        return quantity;
    }

}
