package com.mikaelmamedov.test.controller;

import com.mikaelmamedov.test.model.Socks;
import com.mikaelmamedov.test.service.SocksService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/api/socks")
public class SocksController {
    private final SocksService socksService;

    @Autowired
    public SocksController(SocksService socksService) {
        this.socksService = socksService;
    }

    @GetMapping("/")
    public ResponseEntity<List<Socks>> findAll(){
        List<Socks> socksList = socksService.findAll();
        if(socksList.isEmpty()){
            return ResponseEntity.badRequest().body(null);
        }
        return ResponseEntity.ok(socksList);
    }

    @GetMapping
    public ResponseEntity<List<Socks>> findByParamater(@RequestParam String color, @RequestParam String operation,
                                                       @RequestParam int cottonPart) {
        if(cottonPart <= 0 || cottonPart > 100) {
            return ResponseEntity.badRequest().build();
        }

        Optional<List<Socks>> optionalSocksList = socksService.findByParameter(color, cottonPart, operation);

        if(optionalSocksList.isPresent()){
            return ResponseEntity.ok().body(optionalSocksList.get());
        }

        return ResponseEntity.badRequest().build();
    }

    @PostMapping(value = "/income")
    public ResponseEntity<Socks> addSocksToStock(@RequestBody Socks socks) {
        if(socks.getQuantity() <= 0) {
            return ResponseEntity.badRequest().build();
        }
        Optional<Socks> socksOptional =
                socksService.findByColorAndCottonPart(socks.getColor(), socks.getCottonPart());

        if(socksOptional.isPresent()){
            socksService.updateSocksByQuantity(socksOptional.get(), socks.getQuantity());
            return ResponseEntity.ok().body(socksOptional.get());
        }
        return ResponseEntity.ok().body(socksService.save(socks));
    }

    @PostMapping(value = "/outcome")
    public ResponseEntity<Socks> takeSocksFromStock(@RequestBody Socks socks){
        if(socks.getQuantity() <= 0){
            return ResponseEntity.badRequest().build();
        }

        Optional<Socks> socksOptional =
                socksService.findByColorAndCottonPart(socks.getColor(), socks.getCottonPart());

        if(socksOptional.isPresent()){
            socksService.takeSocksFromStock(socksOptional.get(), socks.getQuantity());
            if(socksOptional.get().getQuantity() < socks.getQuantity()){
                socks.setQuantity(socksOptional.get().getQuantity());
            }
            return ResponseEntity.ok().body(socks);
        }
        else {
            return ResponseEntity.badRequest().build();
        }

    }


}
