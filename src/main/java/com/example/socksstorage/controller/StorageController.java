package com.example.socksstorage.controller;

import com.example.socksstorage.model.Socks;
import com.example.socksstorage.service.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class StorageController {

    private final StorageService storageService;

    @Autowired
    public StorageController(StorageService storageService) {
        this.storageService = storageService;
    }

    @PostMapping(value = "/socks/income")
    public ResponseEntity<?> create(@RequestParam String color,
                                    @RequestParam String cottonPart,
                                    @RequestParam int quantity) {
        if (Integer.parseInt(cottonPart) < 0 ^ Integer.parseInt(cottonPart) > 100) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        for (int i = 0; i < quantity; i++) {
            Socks sock = new Socks();
            sock.setColor(color);
            sock.setCottonPart(Integer.parseInt(cottonPart));
            storageService.create(sock);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping(value = "/socks/outcome")
    public ResponseEntity<Integer> delete(@RequestParam String color,
                                    @RequestParam String quantity,
                                    @RequestParam int cottonPart) {
        ResponseEntity<Integer> responseEntity = read(color, Socks.Operation.equal, cottonPart);
        if (responseEntity.getStatusCodeValue() != 200 || responseEntity.getBody() < Integer.parseInt(quantity)) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        List<Socks> socks = storageService.readAll();

        int counter = Integer.parseInt(quantity);
        for (Socks sock : socks) {
            if (sock.getColor().equals(color) && sock.getCottonPart() == cottonPart) {
                final boolean deleted = storageService.delete(sock.getId());
                if (!deleted) {
                    return new ResponseEntity<Integer>(HttpStatus.INTERNAL_SERVER_ERROR);
                }
                if (counter == 1) {
                    break;
                }
                counter--;
            }
        }
        return new ResponseEntity<Integer>(HttpStatus.OK);
    }

    @GetMapping(value = "/socks")
    public ResponseEntity<Integer> read(@RequestParam(defaultValue = "no") String color,
                                        @RequestParam Socks.Operation operation,
                                        @RequestParam int cottonPart) {
        List<Socks> socks = storageService.readAll();
        if (!"no".equals(color)) {
            socks = socks.stream()
                    .filter(sock -> sock.getColor().equals(color))
                    .collect(Collectors.toList());
        }
        if (operation == Socks.Operation.moreThan) {
            socks = socks.stream()
                    .filter(sock -> sock.getCottonPart() > cottonPart)
                    .collect(Collectors.toList());
        } else if (operation == Socks.Operation.lessThan) {
            socks = socks.stream()
                    .filter(sock -> sock.getCottonPart() < cottonPart)
                    .collect(Collectors.toList());
        } else if (operation == Socks.Operation.equal) {
            socks = socks.stream()
                    .filter(sock -> sock.getCottonPart() == cottonPart)
                    .collect(Collectors.toList());
        }
        return !socks.isEmpty()
                ? new ResponseEntity<Integer>(socks.size(), HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
}
