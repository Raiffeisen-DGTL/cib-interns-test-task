package ru.raiff.raiffsocksstore.rest;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import ru.raiff.raiffsocksstore.entity.enums.ComparingOperation;
import ru.raiff.raiffsocksstore.rest.dto.SocksCounterDto;
import ru.raiff.raiffsocksstore.service.SocksCounterService;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
public class SocksCounterController {

    private final SocksCounterService service;

    @PostMapping("/socks/income")
    @ResponseStatus(HttpStatus.OK)
    public SocksCounterDto income(@RequestBody SocksCounterDto dto) {
        try {
            return service.income(dto);
        } catch (Exception e) {
            log.error("Не удалось произвести приемку товара с цветом: {} и содержанием хлопка: {}",
                    dto.getColor(), dto.getCottonPart(), e);
            throw e;
        }
    }

    @PostMapping("/socks/outcome")
    @ResponseStatus(HttpStatus.OK)
    public SocksCounterDto outcome(@RequestBody SocksCounterDto dto) {
        try {
        return service.outcome(dto);
        } catch (Exception e) {
            log.error("Не удалось произвести списание товара с цветом: {} и содержанием хлопка: {}",
                    dto.getColor(), dto.getCottonPart(), e);
            throw e;
        }
    }

    @GetMapping("/socks")
    @ResponseStatus(HttpStatus.OK)
    public Long getAll(@RequestParam String color,
                                        @RequestParam ComparingOperation operation,
                                        @RequestParam Short cottonPart) {
        return service.getAllByColorAndCottonPartComparing(color, cottonPart, operation);
    }
}
