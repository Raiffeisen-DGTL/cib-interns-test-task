package ru.dnsk.accountingofsocks.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.dnsk.accountingofsocks.model.PairOfSocks;
import ru.dnsk.accountingofsocks.service.PairOfSocksService;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/socks")
public class PairOfSocksController {

    private final PairOfSocksService pairOfSocksService;

    @Autowired
    public PairOfSocksController(PairOfSocksService pairOfSocksService) {
        this.pairOfSocksService = pairOfSocksService;
    }

    @PostMapping("/income")
    public HttpStatus income(@RequestBody @Valid PairOfSocks pairOfSocks,
                             BindingResult bindingResult) {

        ControllerUtils.checkValidityOfParametersAndChangeQuantity(pairOfSocks,
                bindingResult,
                pairOfSocks.getQuantity(),
                pairOfSocksService);

        return HttpStatus.OK;
    }

    @PostMapping("/outcome")
    public HttpStatus outcome(@RequestBody @Valid PairOfSocks pairOfSocks,
                              BindingResult bindingResult) {

        ControllerUtils.checkValidityOfParametersAndChangeQuantity(pairOfSocks,
                bindingResult,
                -pairOfSocks.getQuantity(),
                pairOfSocksService);

        return HttpStatus.OK;
    }

    @GetMapping
    public String pairOfSocks(@RequestParam("color") String color,
                              @RequestParam("operation") String operation,
                              @RequestParam("cottonPart") int cottonPart) {
        return pairOfSocksService.getQuantityByColorAndCottonPart(color, operation, cottonPart);
    }
}
