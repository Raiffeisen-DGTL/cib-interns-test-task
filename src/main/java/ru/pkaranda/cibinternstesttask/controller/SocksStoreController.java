package ru.pkaranda.cibinternstesttask.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.*;
import ru.pkaranda.cibinternstesttask.mapper.common.SocksTransactionMapper;
import ru.pkaranda.cibinternstesttask.model.CountResult;
import ru.pkaranda.cibinternstesttask.model.Request;
import ru.pkaranda.cibinternstesttask.model.dto.SocksTransactionDTO;
import ru.pkaranda.cibinternstesttask.service.SocksTransactionService;

import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

@RestController
@RequiredArgsConstructor
@ComponentScan
public class SocksStoreController {

    private final SocksTransactionService socksTransactionService;
    private final SocksTransactionMapper socksTransactionMapper;


    @Valid
    @GetMapping(path = "/api/socks")
    public CountResult countSocksByColorAndCottonPartAndOperation(@RequestParam String color,
                                                                  @RequestParam String operation,
                                                                  @RequestParam @Min(0) @Max(100) int cottonPart) {

        return socksTransactionService.getNumberOfSocksByColorIdAndCottonPart(color, operation, cottonPart);

    }

    @PostMapping(path = "/api/socks/income")
    public SocksTransactionDTO registerSocksIncome(@RequestBody Request request) {

        return socksTransactionMapper.domainToDto(socksTransactionService.registerIncome(
                request.getColor(),
                request.getCottonPart(),
                request.getQuantity()));

    }


    @PostMapping(path = "/api/socks/outcome")
    public SocksTransactionDTO registerSocksOutcome(@RequestBody Request request) {

        return socksTransactionMapper.domainToDto(socksTransactionService.registerOutcome(
                request.getColor(),
                request.getCottonPart(),
                request.getQuantity()));
    }

}
