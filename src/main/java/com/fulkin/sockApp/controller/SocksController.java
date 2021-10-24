package com.fulkin.sockApp.controller;

import com.fulkin.sockApp.exception.BadRequestException;
import com.fulkin.sockApp.exception.InternalServerException;
import com.fulkin.sockApp.exception.NotFoundException;
import com.fulkin.sockApp.model.Sock;
import com.fulkin.sockApp.service.SockService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Fulkin
 * Created on 20.10.2021
 */

@RestController
@RequestMapping("/api/socks")
public class SocksController {

    private final SockService sockService;
    private static final Logger LOG = LoggerFactory.getLogger(SocksController.class);

    @Autowired
    public SocksController(SockService sockService) {
        this.sockService = sockService;
    }

    @GetMapping
    @ResponseBody
    public ResponseEntity<String> getSocks(@RequestParam(defaultValue = "") String color,
                                           @RequestParam(defaultValue = "") String operation,
                                           @RequestParam(defaultValue = "-1") int cottonPart) {
        if (cottonPart < 0 || cottonPart > 100) {
            LOG.warn("Bad GET request param cottonPart");
            return new ResponseEntity<>("Bad request param: parameter [cottonPart] not in range",
                    HttpStatus.BAD_REQUEST);
        }
        List<Sock> sockList = sockService.getListSock(color, operation, cottonPart);
        if (sockList.isEmpty()) {
            LOG.warn(String.format("No information on the this parameters: color = \"%s\", operation = \"%s\", cottonPart = %s", color, operation, cottonPart));
            return new ResponseEntity<>("There is no information on the specified parameters",
                    HttpStatus.NOT_FOUND);
        }
        int sum = 0;
        for (Sock sock : sockList) {
            sum += sock.getQuantity();
        }
        LOG.info("Socks were found");
        return new ResponseEntity<>(String.valueOf(sum), HttpStatus.OK);
    }

    @RequestMapping(value = "/income", method = RequestMethod.POST, produces = "application/json")
    public ResponseEntity<String> incomeSocks(@RequestBody Sock sock) {

        if (sock.getCottonPart() < 0 || sock.getCottonPart() > 100 || sock.getQuantity() <= 0 || sock.getColor().equals("")) {
            LOG.warn("Bad POST request param cottonPart, quantity or color");
            return new ResponseEntity<>("Bad request param: parameter [cottonPart] not in range, [quantity] less or equal than 0 or [color] field is empty",
                    HttpStatus.BAD_REQUEST);
        }
        try {
            sockService.addSocks(sock);
        } catch (InternalServerException e) {
            LOG.warn(e.getMessage());
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        LOG.info("Socks were accepted");
        return new ResponseEntity<>("Success add income socks", HttpStatus.OK);
    }

    @RequestMapping(value = "/outcome", method = RequestMethod.POST, produces = "application/json")
    public ResponseEntity<String> outcomeSocks(@RequestBody Sock sock) {

        if (sock.getCottonPart() < 0 || sock.getCottonPart() > 100 || sock.getQuantity() <= 0) {
            LOG.warn("Bad POST request param cottonPart or quantity");
            return new ResponseEntity<>("Bad request param: parameter [cottonPart] not in range or [quantity] less or equal than 0",
                    HttpStatus.BAD_REQUEST);
        }
        try {
            sockService.deleteSocks(sock);
        } catch (BadRequestException e) {
            LOG.warn(e.getMessage());
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        } catch (NotFoundException e) {
            LOG.warn(e.getMessage());
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
        LOG.info("Socks have been shipped");
        return new ResponseEntity<>("Success remove outcome socks", HttpStatus.OK);
    }
}
