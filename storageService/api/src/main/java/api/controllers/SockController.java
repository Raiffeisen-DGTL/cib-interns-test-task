package api.controllers;

import api.dto.OperationDTO;
import api.dto.SockDTO;
import api.mappers.OperationMapper;
import api.mappers.SockMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.*;
import service.services.SockService;

@RestController
@RequestMapping("/api/socks")
@RequiredArgsConstructor
public class SockController {

    private final SockService sockService;

    private final SockMapper sockMapper;

    @GetMapping("")
    public ResponseEntity<Integer> getSocks(@RequestParam(value = "color") String color,
                                            @RequestParam(value = "operation") OperationDTO operationDTO,
                                            @RequestParam(value = "cottonPart") int cottonPart) throws MissingServletRequestParameterException {
        int amountSocks = sockService.countSocks(color, cottonPart, OperationMapper.map(operationDTO));
        return new ResponseEntity<Integer>(amountSocks, HttpStatus.OK);
    }

    @PostMapping("/income")
    public ResponseEntity addSocks(@RequestBody SockDTO sockDTO) {
        sockService.addSocks(sockMapper.toEntity(sockDTO));
        return new ResponseEntity(HttpStatus.OK);
    }

    @PostMapping("/outcome")
    public ResponseEntity subtractSocks(@RequestBody SockDTO sockDTO) {
        sockService.subtractSocks(sockMapper.toEntity(sockDTO));
        return new ResponseEntity(HttpStatus.OK);
    }
}
