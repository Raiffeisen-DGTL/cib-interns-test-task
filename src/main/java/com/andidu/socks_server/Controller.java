package com.andidu.socks_server;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;

@RestController
public class Controller {

    private final SocksDao socksDao;

    private final ArrayList<String> operations = new ArrayList<>(
            Arrays.asList("lessThan", "moreThan", "equal")
    );

    public Controller(SocksDao socksDao) {
        this.socksDao = socksDao;
    }

    @GetMapping("/api/socks")
    ResponseEntity<?> get(@RequestParam String color, @RequestParam String operation, @RequestParam Integer cottonPart) {
        if (color == null || operation == null || cottonPart == null
                || color.equals("") || !operations.contains(operation)
                || (cottonPart > 100) || (cottonPart < 0)) {
            return new ResponseEntity<>(0, HttpStatus.valueOf(400));
        }
        int sum = 0;
        for (Integer s : socksDao.getSocks(color, operation, cottonPart)) {
            if (s == null) {
                return new ResponseEntity<>(sum, HttpStatus.valueOf(500));
            }
            sum += s;
        }
        return new ResponseEntity<>(sum, HttpStatus.valueOf(200));
    }

    @PostMapping(value = "/api/socks/income")
    ResponseEntity<?> income(@RequestBody Socks socks) {
        //if socks variable cannot be associated with request's body
        // method returns 400 (by default) [as required in the task]
        //if some kind of error happens during computation
        // server returns 500 (by default) [as required in the task]
        if (postMethodArgsCheck(socks)) {
            return new ResponseEntity<>(HttpStatus.valueOf(400));
        }
        socksDao.addSocks(socks);
        return new ResponseEntity<>(HttpStatus.valueOf(200));
    }

    @PostMapping("/api/socks/outcome")
    ResponseEntity<?>  outcome(@RequestBody Socks socks) {
        if (postMethodArgsCheck(socks)) {
            return new ResponseEntity<>(HttpStatus.valueOf(400));
        }
        int code = socksDao.subtractSocks(socks);
        if (code == SocksDao.INCORRECT_ARGUMENT) {
            return new ResponseEntity<>(HttpStatus.valueOf(400));
        }
        return new ResponseEntity<>(HttpStatus.valueOf(200));
    }

    private boolean postMethodArgsCheck(Socks socks) {
        return socks.getColor() == null || socks.getCottonPart() == null || socks.getQuantity() == null ||
                socks.getQuantity() <= 0 || socks.getColor().equals("") ||
                socks.getCottonPart() < 0 || socks.getCottonPart() > 100;
    }
}
