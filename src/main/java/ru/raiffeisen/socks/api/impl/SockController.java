package ru.raiffeisen.socks.api.impl;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import ru.raiffeisen.socks.api.SockApi;
import ru.raiffeisen.socks.service.impl.Operation;
import ru.raiffeisen.socks.dto.SocksQuantityRequest;
import ru.raiffeisen.socks.dto.SocksRequest;
import ru.raiffeisen.socks.dto.SocksResponse;
import ru.raiffeisen.socks.service.SockService;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/socks")
@AllArgsConstructor
public class SockController implements SockApi {

    private final SockService sockService;

    @PostMapping(value = "/income", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.OK)
    public void registerArrivalOfSocks(@Valid SocksRequest socksRequest) {
        sockService.registerArrivalOfSocks(socksRequest.color,
                socksRequest.cottonPart,
                socksRequest.quantity);
    }

    @PostMapping(value = "/outcome", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.OK)
    public void registerReleaseOfSocks(@Valid SocksRequest socksRequest) {
        sockService.registerReleaseOfSocks(socksRequest.color,
                socksRequest.cottonPart,
                socksRequest.quantity);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<SocksResponse> getQuantityOfSocks(@Valid SocksQuantityRequest request) {
        int quantityOfSocks = sockService.getQuantityOfSocks(
                request.color,
                Operation.byValue(request.operation),
                request.cottonPart);

        return ResponseEntity.ok(new SocksResponse(quantityOfSocks));
    }
}