package ru.danilarassokhin.raiffeisensocks.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.danilarassokhin.raiffeisensocks.dto.ResponseDto;
import ru.danilarassokhin.raiffeisensocks.model.SocksColor;
import ru.danilarassokhin.raiffeisensocks.service.SocksService;

import javax.validation.ConstraintViolationException;
import java.util.Set;

import static ru.danilarassokhin.raiffeisensocks.Url.API_ENDPOINT;
import static ru.danilarassokhin.raiffeisensocks.Url.SOCK;

@RestController
@RequestMapping(API_ENDPOINT + SOCK.ENDPOINT)
@Validated
public class SockController {

    private SocksService sockService;

    @Autowired
    public SockController(SocksService sockService) {
        this.sockService = sockService;
    }

    @GetMapping(SOCK.ALL_COLORS)
    @ResponseStatus(HttpStatus.OK)
    public ResponseDto<Set<SocksColor>> getAllSockColors() {
        return new ResponseDto<>(
                Set.of(
                        SocksColor.values()
                )
        );
    }


    @ExceptionHandler(value = ConstraintViolationException.class)
    private void handleConstraintViolationException(ConstraintViolationException exception) {

    }

}
