package com.example.Inventory.controllers;

import com.example.Inventory.entities.SocksEntity;
import com.example.Inventory.exception.ApiRequestException;
import com.example.Inventory.repos.SocksRepository;
import com.example.Inventory.services.Operation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/socks")
public class InventoryController {
    @Autowired
    private SocksRepository socksRepo;

    @GetMapping ()
    public List<SocksEntity> getSocks(
            @RequestParam String color, @RequestParam Operation operation, @RequestParam Integer cottonPart
    ) {

        List<SocksEntity> socks = new ArrayList<SocksEntity>();

        switch(operation){
            case moreThan:
                socks = socksRepo.findByCottonPartGreaterThanAndColor(cottonPart, color);
                break;
            case lessThan:
                socks = socksRepo.findByCottonPartLessThanAndColor(cottonPart, color);
                break;
            case equal:
                socks.add(socksRepo.findByColorAndCottonPart(color, cottonPart));
                break;
            default:
                throw new ApiRequestException("Неверный оператор сравнения");
        }

        return socks;
    }

    @PostMapping("/income")
    public ResponseEntity socksIncome(
            @RequestBody(required = false) @Valid SocksEntity socksEntity,
            BindingResult bindingResult
    ) {
        if (bindingResult.hasErrors())
            throw new ApiRequestException(bindingResult.getFieldErrors().stream().collect(Collectors.toMap(
                    fieldError -> fieldError.getField() + "Error",
                    FieldError::getDefaultMessage
            )).toString());

        SocksEntity socksInBD = socksRepo.findByColorAndCottonPart(socksEntity.getColor(), socksEntity.getCottonPart());
        if (socksInBD != null) {
            socksInBD.setQuantity(socksInBD.getQuantity() + socksEntity.getQuantity());
            socksRepo.save(socksInBD);
        } else {
            socksRepo.save(socksEntity);
        }
        return ResponseEntity.ok("Приход зарегистрирован");
    }

    @PostMapping("/outcome")
    public ResponseEntity socksOutcome(
            @RequestBody(required = false) @Valid SocksEntity socksEntity,
            BindingResult bindingResult
    ) {
        if (bindingResult.hasErrors())
            throw new ApiRequestException(bindingResult.getFieldErrors().stream().collect(Collectors.toMap(
                    fieldError -> fieldError.getField() + "Error",
                    FieldError::getDefaultMessage
            )).toString());

        SocksEntity socksInBD = socksRepo.findByColorAndCottonPart(socksEntity.getColor(), socksEntity.getCottonPart());

        if (socksInBD != null && socksInBD.getQuantity() >= socksEntity.getQuantity()) {
                socksInBD.setQuantity(socksInBD.getQuantity() - socksEntity.getQuantity());
                if (socksInBD.getQuantity() > 0)
                    socksRepo.save(socksInBD);
                else
                    socksRepo.delete(socksInBD);
        } else {
            throw new ApiRequestException("Запрашиваемое количество превышает имеющееся на складе");
        }
        return ResponseEntity.ok("Отпуск зарегистрирован");
    }
}
