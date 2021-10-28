package com.n75jr.apitesttask.socks;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class SockController {
    private final SockService sockService;

    @Autowired
    public SockController(SockService sockService) {
        this.sockService = sockService;
    }

    @PostMapping("/api/socks/income")
    public boolean add(@RequestBody Sock sock) {
        long val = sockService.getSize();
        sockService.income(sock);
        return val != sockService.getSize();
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
