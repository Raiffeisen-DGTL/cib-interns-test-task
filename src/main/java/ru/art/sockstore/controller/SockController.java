package ru.art.sockstore.controller;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import ru.art.sockstore.dataBase.Operation;
import ru.art.sockstore.dataBase.SockConnection;
import ru.art.sockstore.model.Sock;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;


@RestController
public class SockController {
    private final SockConnection sockConnection;

    public SockController() {
        this.sockConnection = new SockConnection();
    }

    @GetMapping("/api/socks/all")
    ArrayList<Sock> all() {
        return sockConnection.getAll();
    }

    @GetMapping("/api/socks/{id}")
    Sock getByOne(@PathVariable Integer id) {
        return sockConnection.getById(id);
    }

    @GetMapping("/api/socks")
    ResponseEntity<Integer> getSuperQuery(@RequestParam String color, @RequestParam Operation operation, @RequestParam Integer cottonPart) {
        if (!isGoodParam(color, operation, cottonPart)) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        Integer result = sockConnection.getSuperQuery(color, operation, cottonPart);
        return (result == sockConnection.GET_SUPER_QUERY_ERR) ?
                new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR) :
                new ResponseEntity<>(result, HttpStatus.OK);
    }

    private boolean isGoodParam(String color, Operation operation, Integer cottonPart) {
        return color != null &&
                operation != null &&
                cottonPart >= 0 &&
                cottonPart <= 100;
    }

    @PostMapping("/api/socks/income")
    ResponseEntity<Sock> newSock(@RequestBody Sock newSock) {
        if (!Sock.isGood(newSock)) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return sockConnection.newSock(newSock) ?
                new ResponseEntity<>(newSock, HttpStatus.OK) :
                new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @PostMapping("/api/socks/outcome")
    ResponseEntity<String> deleteSock(@RequestBody Sock deletedSock) {
        if (!Sock.isGood(deletedSock)) {
            return new ResponseEntity<>("Bad request", HttpStatus.BAD_REQUEST);
        }
        String result = sockConnection.deleteSock(deletedSock);

        return (result.equals(sockConnection.SUCCESS)) ?
                new ResponseEntity<>(result, HttpStatus.OK) :
                new ResponseEntity<>(result, HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
