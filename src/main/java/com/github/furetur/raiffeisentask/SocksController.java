package com.github.furetur.raiffeisentask;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;

@RestController
public class SocksController {

    private SocksRepository socksRepository;

    public SocksController(SocksRepository socksRepository) {
        this.socksRepository = socksRepository;
    }

    @GetMapping("/api/socks")
    public int countSocks(
            @RequestParam(name = "color") String color,
            @RequestParam(name = "operation") String operation,
            @RequestParam(name = "cottonPart") int cottonPart
    ) {
        if (color == null) return 0;
        var sum = socksRepository.findAll().stream().filter(socks -> Objects.equals(socks.getColor(), color)).map(Socks::getQuantity).reduce(0, Integer::sum);
        return sum;
    }
}
