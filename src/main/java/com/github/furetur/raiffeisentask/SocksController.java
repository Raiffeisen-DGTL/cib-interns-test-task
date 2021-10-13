package com.github.furetur.raiffeisentask;

import org.springframework.http.HttpStatus;
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

    @PostMapping(value = "api/socks/outcome", consumes = "application/json")
    public void outcomeSocks(@RequestBody SocksRecord socks) {
        socksRepository.removeSocks(socks.color(), socks.cottonPart(), socks.quantity());
    }

    @ExceptionHandler(NotEnoughSocksException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public @ResponseBody String handleException(NotEnoughSocksException e) {
        return "That doesnt work";
    }
}
