package ru.akkulov.raiffeisen.controller;

import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.akkulov.raiffeisen.model.Sock;
import ru.akkulov.raiffeisen.service.SockService;
import ru.akkulov.raiffeisen.util.Operation;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/socks")
public class SockController {
    private final SockService sockService;

    @PostMapping("/income")
    @ApiOperation(value = "Create Sock")
    public ResponseEntity<Sock> addSock(@RequestBody Sock sock) {
        return ResponseEntity.ok(sockService.createSock(sock));
    }

    @PostMapping("/outcome")
    @ApiOperation(value = "Remove socks from stock")
    public ResponseEntity<Sock> outComeSocks(@RequestBody Sock comingSock) {
        return ResponseEntity.ok(sockService.getSockByColorAndCottonPart(comingSock));
    }

    @GetMapping
    @ApiOperation(value = "Get socks quantity by request parameters")
    public ResponseEntity<String> getQuantityByParameters(@RequestParam String color,
                                                          @RequestParam Operation operation,
                                                          @RequestParam int cottonPart) {

        return ResponseEntity.ok(sockService.getQuantityByParameters(color, operation, cottonPart));
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "Get Sock by Id")
    public ResponseEntity<Sock> getSocksById(@PathVariable long id) {
        return ResponseEntity.ok(sockService.getSocksById(id));
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "Delete Sock by Id")
    public ResponseEntity<Sock> deleteSocksById(@PathVariable long id) {
        sockService.deleteSocksById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/all-socks")
    @ApiOperation(value = "Show all socks from stock")
    public ResponseEntity<List<Sock>> getAllSocks() {
        return ResponseEntity.ok(sockService.getAllSocks());
    }
}
