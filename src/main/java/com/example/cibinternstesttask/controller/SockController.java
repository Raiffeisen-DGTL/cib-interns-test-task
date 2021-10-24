package com.example.cibinternstesttask.controller;

import com.example.cibinternstesttask.dto.SockDto;
import com.example.cibinternstesttask.model.Sock;
import com.example.cibinternstesttask.repository.SockRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/socks")
public class SockController {

    private final SockRepository sockRepository;

    @Autowired
    public SockController(SockRepository sockRepository) {
        this.sockRepository = sockRepository;
    }

    @PostMapping("/income")
    public Sock incomeSocks(@RequestBody SockDto sockDto) {
        String color = sockDto.getColor();
        Long cottonPart = sockDto.getCottonPart();
        Long addedQuantity = sockDto.getQuantity();

        if (color == null || cottonPart == null || addedQuantity == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }

        if (sockRepository.findByColorAndCottonPart(color, cottonPart) == null) {
            sockRepository.save(new Sock(color, cottonPart, 0L));
        }
        Sock sock = sockRepository.findByColorAndCottonPart(color, cottonPart);
        sock.setQuantity(sock.getQuantity() + addedQuantity);
        sockRepository.save(sock);

        return sock;
    }

    @PostMapping("/outcome")
    public Sock outcomeSocks(@RequestBody SockDto sockDto) {
        String color = sockDto.getColor();
        Long cottonPart = sockDto.getCottonPart();
        Long removedQuantity = sockDto.getQuantity();

        if (color == null || cottonPart == null || removedQuantity == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }

        Sock sock = sockRepository.findByColorAndCottonPart(color, cottonPart);
        if (sock == null || sock.getQuantity() - removedQuantity < 0) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }

        sock.setQuantity(sock.getQuantity() - removedQuantity);
        sockRepository.save(sock);
        return sock;
    }

    @GetMapping("")
    public Long getSocks(@RequestParam String color,
                           @RequestParam String operation,
                           @RequestParam(name = "cottonPart") Long requestedCottonPart) {
        List<Sock> socks;

        switch (operation) {
            case ("lessThan"):
                socks = sockRepository.findAllByColorAndCottonPartLessThan(color, requestedCottonPart);
                break;
            case "equals":
                socks = sockRepository.findAllByColorAndCottonPartEquals(color, requestedCottonPart);
                break;
            case "moreThan":
                socks = sockRepository.findAllByColorAndCottonPartGreaterThan(color, requestedCottonPart);
                break;
            default:
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
        return socks.stream().map(Sock::getQuantity).reduce(0L, Long::sum);
    }
}
