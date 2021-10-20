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

@RestController
@RequestMapping("/api/socks")
public class SockController {

    @Autowired
    private SockRepository sockRepository;

    @PostMapping("/income")
    public ResponseEntity incomeSocks(@Validated @RequestBody Sock sock){
        Sock ifExist;
        ifExist = sockRepository.findByCottonPartAndColor(sock.getCottonPart(),sock.getColor());
        if(ifExist != null){
            ifExist.setQuantity(sock.getQuantity() + ifExist.getQuantity());
            sockRepository.save(ifExist);
            return ResponseEntity.ok("Успешно добавлено");
        }
        sockRepository.save(sock);
        return ResponseEntity.ok("Товар создан");
    }

    @PostMapping("/outcome")
    public ResponseEntity outcomeSocks(@Validated @RequestBody Sock sock){
         Sock ifExist;
         ifExist = sockRepository.findByCottonPartAndColor(sock.getCottonPart(),sock.getColor());
         if (ifExist == null || ifExist.getQuantity() < sock.getQuantity()){
             return new ResponseEntity(HttpStatus.BAD_REQUEST);
         }
        ifExist.setQuantity(ifExist.getQuantity() - sock.getQuantity());
        sockRepository.save(ifExist);
        return new ResponseEntity("Убыль товара успешно зафиксированна",HttpStatus.OK);
    }

    @GetMapping("/")
    public ResponseEntity typeCount(@RequestParam String color,@RequestParam Integer cottonPart,@RequestParam String operation){
        long resultCount = 0;
        String answer = "Найдено результатов по вашему запросу:";
        if(Objects.equals(operation, "moreThan")){
            resultCount = sockRepository.findByCottonPartGreaterThanAndColor(cottonPart,color).stream().count();
            return new ResponseEntity(answer + resultCount,HttpStatus.OK);
        }
        if(Objects.equals(operation, "lessThan")){
            resultCount = sockRepository.findByCottonPartLessThanAndColor(cottonPart,color).stream().count();
            return new ResponseEntity(answer + resultCount,HttpStatus.OK);
        }
        if(Objects.equals(operation, "equal")){
            resultCount = sockRepository.findByColorAndCottonPart(color,cottonPart).stream().count();
            return new ResponseEntity(answer + resultCount,HttpStatus.OK);
        }
        return new ResponseEntity(HttpStatus.BAD_REQUEST);

    }



}
