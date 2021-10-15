package ru.raiff.raiffsocksstore.rest;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import ru.raiff.raiffsocksstore.rest.dto.SocksCategoryDto;
import ru.raiff.raiffsocksstore.service.SocksCategoryService;

@Slf4j
@RestController
@RequiredArgsConstructor
public class SocksCategoryController {

    private final SocksCategoryService service;

    @PostMapping("/socks-category")
    @ResponseStatus(HttpStatus.OK)
    public SocksCategoryDto create(@RequestBody SocksCategoryDto dto) {
        try {
            return service.create(dto);
        } catch (Exception e) {
            log.error("не удалось сохранить категорию", e);
            throw e;
        }
    }
}
