package ru.dnsk.accountingofsocks.controller;

import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.server.ResponseStatusException;
import ru.dnsk.accountingofsocks.model.PairOfSocks;
import ru.dnsk.accountingofsocks.service.PairOfSocksService;

public class ControllerUtils {

    private ControllerUtils() {
    }

    public static PairOfSocks checkValidityOfParametersAndChangeQuantity(PairOfSocks pairOfSocks,
                                                                         BindingResult bindingResult,
                                                                         int quantity,
                                                                         PairOfSocksService pairOfSocksService) {
        if (bindingResult.hasErrors()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }

        return pairOfSocksService.changeQuantity(pairOfSocks.getColor(), pairOfSocks.getCottonPart(), quantity);
    }
}
