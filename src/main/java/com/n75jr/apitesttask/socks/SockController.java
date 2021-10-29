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
    public ResponseEntity<Sock> outcome(@RequestBody String sock) throws IOException {
        Map<String, Object> params = new ObjectMapper().readValue(sock, new TypeReference<Map<String, Object>>() {});

        String color = (String)params.get("color");
        Integer countPart = (Integer)params.get("cottonPart");
        if (color == null || countPart == null)
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        if (countPart < 0 || countPart > 100)
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        ResponseEntity<Sock> response = null;
        response = sockService.outcome(new Sock(color, countPart)) != 0
                ? new ResponseEntity<Sock>(HttpStatus.OK)
                : new ResponseEntity<Sock>(HttpStatus.BAD_REQUEST);
        return response;
    }

    @GetMapping("/api/socks")
    ResponseEntity<Long> operation(@RequestParam(name = "color") String color,
                  @RequestParam(name = "cottonPart") int cotton_part,
                  @RequestParam(name = "operation") String operation) {
        if (cotton_part < 0 || cotton_part > 100)
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        String operationName = operation.toLowerCase();
        long result = 0;
        switch (operationName) {
            case "morethan":
                result = sockService.operMoreThan(color, cotton_part);
                break;
            case "equal":
                result = sockService.operEqual(color, cotton_part);
                break;
            case "lessthan":
                result = sockService.operLessThan(color, cotton_part);
                break;
            default:
                return new ResponseEntity<Long>(HttpStatus.BAD_REQUEST);
        }
         return new ResponseEntity<Long>(result, HttpStatus.OK);
    }

    @GetMapping("/api/socks/all")
    ResponseEntity<List<Sock>> getAll() {
        return new ResponseEntity<>(sockService.getAll(), HttpStatus.OK);
    }

}
