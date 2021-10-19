package com.raiffeisen.raiffeisen_test_task.controller.v1.api;

import com.raiffeisen.raiffeisen_test_task.model.Operation;
import com.raiffeisen.raiffeisen_test_task.model.Socks;
import com.raiffeisen.raiffeisen_test_task.service.SocksService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping(path = "/api/socks",
        produces = MediaType.APPLICATION_JSON_VALUE)
public class SocksController {
    private final SocksService socksService;

    @Autowired
    public SocksController(SocksService socksService) {
        this.socksService = socksService;
    }

    @PostMapping("/income")
    public void addSocks(@RequestBody Socks socks) {
        socksService.addSocks(socks);
    }

    @PostMapping("/outcome")
    public void decreaseSocks(@RequestBody Socks socks) {
        socksService.decreaseSocks(socks);
    }

    @GetMapping()
    public Socks getSocks(@RequestParam("color") final String color,
                          @RequestParam("operation") final Operation operation,
                          @RequestParam("cottonPart") final byte cottonPart) {
        return socksService.getSocks(color, operation, cottonPart);
    }
}
