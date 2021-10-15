package com.home.sock.controller;

import com.home.sock.dto.SocksIncomeOutcomeDto;
import com.home.sock.repository.SockRepository;
import com.home.sock.service.SockService;


import javax.validation.ConstraintViolationException;
import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

@RestController
@Validated
@RequestMapping("/api/socks")
public class SockController {

    @Autowired
    SockRepository sockRepository;

    final SockService sockService;

    @Autowired
    public SockController(SockService sockService) {
        this.sockService = sockService;
    }

    @GetMapping()
    ResponseEntity<String> countByCondition(
            @RequestParam @NotBlank String color,
            @RequestParam @Min(0) @Max(100) int cotton_part,
            @RequestParam @NotBlank String operation
    ) {
        Integer res;
        color = color.trim();
        switch (operation) {
            case ("equal") -> res = this.sockRepository.findByColorAndCottonPartEquals(color, cotton_part);
            case ("moreThan") -> res = this.sockRepository.findByColorAndCottonPartGreaterThan(color, cotton_part);
            case ("lessThan") -> res = this.sockRepository.findByColorAndCottonPartLessThan(color, cotton_part);
            default -> {
                return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
            }
        }
        if (res == null) res = 0;
        return new ResponseEntity<>(res.toString(), HttpStatus.OK);
    }

    @PostMapping("/income")
    ResponseEntity<Object> income(@RequestBody @Valid SocksIncomeOutcomeDto dto) {
        this.sockService.income(dto);
        return new ResponseEntity<>("Удалось  добавить приход", HttpStatus.OK);
    }

    //Если quantity в запросе на списание больше, чем в значение в БД, то является ли запрос BadRequest?
    @PostMapping("/outcome")
    ResponseEntity<Object> outcome(@RequestBody @Valid SocksIncomeOutcomeDto dto) {
        boolean res;
        res = this.sockService.outcome(dto);
        if (res)
            return new ResponseEntity<>("Удалось учесть расход", HttpStatus.OK);
        else
            return new  ResponseEntity<>("Не удалось учесть расход", HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({
            ConstraintViolationException.class,
            MethodArgumentTypeMismatchException.class,
            MethodArgumentNotValidException.class,
            HttpMessageNotReadableException.class
    })
    public ResponseEntity<Object> handleValidateException(Exception exc) {
        return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({
            HttpServerErrorException.class,
    })
    public ResponseEntity<Object> handleInternalServerException(Exception exc) {
        return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}