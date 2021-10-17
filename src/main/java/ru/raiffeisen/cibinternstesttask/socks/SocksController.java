package ru.raiffeisen.cibinternstesttask.socks;

import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import ru.raiffeisen.cibinternstesttask.socks.dto.QuantityDto;
import ru.raiffeisen.cibinternstesttask.socks.dto.SocksDto;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class SocksController {

    private final SocksService socksService;

    @PostMapping("/socks/income")
    @ResponseStatus(HttpStatus.OK)
    public void income(@Valid @RequestBody SocksDto socks) {
        socksService.income(socks);
    }

    @PostMapping("/socks/outcome")
    @ResponseStatus(HttpStatus.OK)
    public void outcome(@Valid @RequestBody SocksDto socks) {
        socksService.outcome(socks);
    }

    @GetMapping("/socks")
    public QuantityDto getAllSocks(@RequestParam String color,
                                   @RequestParam String operation,
                                   @RequestParam Short cottonPart) {
        return socksService.getSocksQuantity(color, operation, cottonPart);
    }
}
