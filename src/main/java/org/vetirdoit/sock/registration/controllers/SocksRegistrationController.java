package org.vetirdoit.sock.registration.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.ConverterRegistry;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.vetirdoit.sock.registration.domain.BiPredicate;
import org.vetirdoit.sock.registration.domain.Color;
import org.vetirdoit.sock.registration.dtos.utils.DtoConverter;
import org.vetirdoit.sock.registration.dtos.SockTypeDto;
import org.vetirdoit.sock.registration.services.SockRegistrationService;

@RestController("/api")
public class SocksRegistrationController {
    private final SockRegistrationService sockRegistrationService;

    @Autowired
    public SocksRegistrationController(SockRegistrationService sockRegistrationService) {
        this.sockRegistrationService = sockRegistrationService;
    }

    @InitBinder
    protected void initBinder(ConverterRegistry registry) {
        registry.addConverter(new DtoConverter.StringToBiPredicateConverter());
        registry.addConverter(new DtoConverter.StringToColorConverter());
    }

    @PostMapping("/socks/income")
    public void registerIncomingSocks(@RequestBody SockTypeDto sockTypeDto) {

        sockRegistrationService.registerIncomingSocks( sockTypeDto );
    }

    @PostMapping("/socks/outcome")
    public void registerOutgoingSocks(@RequestBody SockTypeDto sockTypeDto) {

        boolean isSuccessful = sockRegistrationService.registerOutgoingSocks( sockTypeDto );
            if ( !isSuccessful )
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "You requested too many socks!");
    }

    @GetMapping("/socks")
    public long getAllRequiredSocks(@RequestParam Color color,
                                    @RequestParam("operation") BiPredicate operation,
                                    @RequestParam int cottonPart) {

        return sockRegistrationService.getCountOfRequiredSocks(color, operation, cottonPart);
    }
}
