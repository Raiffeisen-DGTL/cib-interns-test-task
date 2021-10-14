package ru.pkaranda.cibinternstesttask.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.pkaranda.cibinternstesttask.mapper.common.SocksTransactionMapper;
import ru.pkaranda.cibinternstesttask.model.CountResult;
import ru.pkaranda.cibinternstesttask.model.dto.SocksTransactionDTO;
import ru.pkaranda.cibinternstesttask.service.SocksTransactionService;

@RestController
@RequiredArgsConstructor
public class SocksStoreController {

    private final SocksTransactionService socksTransactionService;
    private final SocksTransactionMapper socksTransactionMapper;


    @GetMapping(path = "/api/socks")
    public CountResult countSocksByColorAndCottonPartAndOperation(@RequestParam String color,
                                                                  @RequestParam String operation,
                                                                  @RequestParam int cottonPart) {

        return socksTransactionService.getNumberOfSocksByColorIdAndCottonPart(color, operation, cottonPart);

    }

    @PostMapping(path = "/api/socks/income")
    public SocksTransactionDTO registerSocksIncome(@RequestParam String color,
                                                   @RequestParam int cottonPart,
                                                   @RequestParam int quantity) {

        return socksTransactionMapper.domainToDto(socksTransactionService.registerIncome(color, cottonPart, quantity));

    }


    @PostMapping(path = "/api/socks/outcome")
    public SocksTransactionDTO registerSocksOutcome(@RequestParam String color,
                                                    @RequestParam int cottonPart,
                                                    @RequestParam int quantity) {

        return socksTransactionMapper.domainToDto(socksTransactionService.registerOutcome(color, cottonPart, quantity));
    }

}
