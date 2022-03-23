package com.zpsx.cibinternstesttask.controller;

import com.zpsx.cibinternstesttask.domain.Operation;
import com.zpsx.cibinternstesttask.domain.SockStock;
import com.zpsx.cibinternstesttask.repo.SockStockRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("api/socks")
public class SocksController {

    @Autowired
    SockStockRepository sockStockRepo;

    @GetMapping
    public ResponseEntity<String> getSocksFiltered(@RequestParam String color,
                                   @RequestParam Operation operation,
                                   @RequestParam Byte cottonPart){
        List<SockStock> sockStocks = new ArrayList<>();

        switch (operation){
            case equal: sockStocks = sockStockRepo.findAllByColorAndCottonPart(color, cottonPart); break;
            case moreThan: sockStocks = sockStockRepo.findAllByColorAndCottonPartGreaterThan(color, cottonPart); break;
            case lessThan: sockStocks = sockStockRepo.findAllByColorAndCottonPartLessThan(color, cottonPart); break;
        }

        Long quantity = 0L;
        for(SockStock sockStock: sockStocks){
            quantity += sockStock.getQuantity();
        }

        return ResponseEntity.ok(quantity.toString());
    }

    @PostMapping("income")
    public ResponseEntity<Object> addSocks(@RequestBody @Valid SockStock income) {
        SockStock sockStock = sockStockRepo.findByColorAndCottonPart(income.getColor(), income.getCottonPart());
        if(sockStock==null)
            sockStock = income;
        else
            sockStock.setQuantity(sockStock.getQuantity() + income.getQuantity());

        sockStockRepo.save(sockStock);

        return ResponseEntity.ok(null);
    }

    @PostMapping("outcome")
    public ResponseEntity<Object> withdrawSocks(@RequestBody @Valid SockStock withdraw){
        SockStock sockStock = sockStockRepo.findByColorAndCottonPart(withdraw.getColor(), withdraw.getCottonPart());

        if(!(sockStock != null && sockStock.getQuantity() - withdraw.getQuantity() >= 0))
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);

        sockStock.setQuantity(sockStock.getQuantity() - withdraw.getQuantity());
        sockStockRepo.save(sockStock);

        return ResponseEntity.ok(null);
    }
}
