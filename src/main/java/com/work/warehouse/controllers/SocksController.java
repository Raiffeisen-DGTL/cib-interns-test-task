package com.work.warehouse.controllers;

import com.work.warehouse.dto.SocksDTO;
import com.work.warehouse.entities.Socks;
import com.work.warehouse.exceptions.SocksExistException;
import com.work.warehouse.facade.SocksFacade;
import com.work.warehouse.payload.response.MessageResponse;
import com.work.warehouse.services.SocksService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequestMapping(value = "/api/socks")
public class SocksController {

    @Autowired
    private SocksService socksService;
    @Autowired
    private SocksFacade socksFacade;

    @RequestMapping(value = "/income", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<Object> addSocks(@Valid @RequestBody SocksDTO socksDTO) throws SocksExistException {

        Socks socks = socksService.income(socksDTO);
        SocksDTO addSocks = socksFacade.socksToSocksDTO(socks);

        return new ResponseEntity<>(addSocks, HttpStatus.OK);
    }

    @RequestMapping(value = "/outcome", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<Object> outcomeSocks(@Valid @RequestBody SocksDTO socksDTO) throws SocksExistException {

        Socks socks = socksService.outcome(socksDTO);
        SocksDTO outcomeSocks = socksFacade.socksToSocksDTO(socks);

        return new ResponseEntity<>(outcomeSocks, HttpStatus.OK);
    }

    @RequestMapping (value = "/delete/{id}", method = RequestMethod.POST)
    public ResponseEntity<MessageResponse> deleteSocks(@PathVariable("id") String id){
        socksService.delete(Long.parseLong(id));
        return new ResponseEntity<>(new MessageResponse("Socks delete"), HttpStatus.OK);
    }

    @GetMapping("/{color}")
    public ResponseEntity<List<SocksDTO>> getAllSocks(@PathVariable("color") String color){
        List<SocksDTO> socksDTOList = socksService.getAllSocksByColor(color)
                .stream()
                .map(socksFacade::socksToSocksDTO)
                .collect(Collectors.toList());

        return new ResponseEntity<>(socksDTOList,HttpStatus.OK);
    }
}
