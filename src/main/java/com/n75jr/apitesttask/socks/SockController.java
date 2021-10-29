package com.n75jr.apitesttask.socks;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@RestController
public class SockController {
    private final SockService sockService;

    @Autowired
    public SockController(SockService sockService) {
        this.sockService = sockService;
    }

    @PostMapping("/api/socks/income")
    public ResponseEntity<Sock> income(@RequestBody String sock) throws IOException {
        Map<String, Object> params = new ObjectMapper().readValue(sock, new TypeReference<Map<String, Object>>() {});

        String color = (String)params.get("color");
        Integer countPart = (Integer)params.get("cottonPart");
        if (color == null || countPart == null)
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        if (countPart < 0 || countPart > 100)
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        ResponseEntity<Sock> response = null;
        response = sockService.income(new Sock(color, countPart)) == 1
                ? new ResponseEntity<Sock>(HttpStatus.OK)
                : new ResponseEntity<Sock>(HttpStatus.INTERNAL_SERVER_ERROR);
        return response;
    }

    @PostMapping("/api/socks/outcome")
    public boolean remove(@RequestBody Sock sock) {
        long val = sockService.getSize();
        System.out.println(sock);
        if (sock.getId() == 0) {
            sockService.outcomeWithoutId(sock.getColor(), sock.getCottonPart());
        } else
            sockService.outcome(sock);
        return val != sockService.getSize();
    }

    @GetMapping("/api/socks")
    long operation(@RequestParam(name = "color") String color,
                  @RequestParam(name = "cottonPart") int cotton_part,
                  @RequestParam(name = "operation") String operation) {
        String operName = operation.toLowerCase();
        long res = 0;
        switch (operName) {
            case "morethan":
                res = sockService.operMoreThan(color, cotton_part);
                break;
            case "equal":
                res = sockService.operEqual(color, cotton_part);
                break;
            case "moreThan":
                res = sockService.operMoreThan(color, cotton_part);
                break;
            default:
                throw new IllegalStateException("Not correct operation");
        }
        return res;
    }

    @GetMapping("/all")
    List<Sock> testAll() {
        return sockService.testAll();
    }

}
