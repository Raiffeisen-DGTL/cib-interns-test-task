package ru.raiffeisen.socksapp.Controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.raiffeisen.socksapp.Model.Socks;
import ru.raiffeisen.socksapp.Service.SocksService;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/socks")
@RequiredArgsConstructor
@Validated
public class SocksController {

    private final SocksService socksService;

    @GetMapping("")
    ResponseEntity<?> getQuantityOfSocksByParams(@Valid @RequestParam("color") String color, @Valid @RequestParam("operation") String operation,
                                                 @Valid @RequestParam("cottonPart") int cottonPart) {
        return ResponseEntity.ok(socksService.getQuantityOfSocks(color, operation, cottonPart));
    }

    @PostMapping("/income")
    ResponseEntity<?> addSocks(@Valid @RequestBody Socks socks) {
        socksService.addSocks(socks);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/outcome")
    ResponseEntity<?> removeSocks(@Valid @RequestBody Socks socks) {
        socksService.removeSocks(socks);
        return ResponseEntity.ok().build();
    }

}
