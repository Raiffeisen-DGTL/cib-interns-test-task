package com.github.furetur.raiffeisentask;

import org.springframework.web.bind.annotation.*;


@RestController
public class SocksController {

    private final SocksRepository socksRepository;

    public SocksController(SocksRepository socksRepository) {
        this.socksRepository = socksRepository;
    }

    @GetMapping("/api/socks")
    public int countSocks(
            @RequestParam(name = "color") String color,
            @RequestParam(name = "operation") OperationTypes operation,
            @RequestParam(name = "cottonPart") int cottonPart
    ) {
        return switch (operation) {
            case EQUAL -> socksRepository.countByColorAndCottonPart(color, cottonPart);
            case LESS_THAN -> socksRepository.countByColorAndCottonPartLessThan(color, cottonPart);
            case MORE_THAN -> socksRepository.countByColorAndCottonPartGreaterThan(color, cottonPart);
        };
    }

    @PostMapping(value = "api/socks/income", consumes = "application/json")
    public void incomeSocks(@RequestBody SocksRecord socks) {
        socksRepository.addSocks(socks.color(), socks.cottonPart(), socks.quantity());
    }
}
