package com.AnnaMarunko.cibinternstesttask.controllers;


import com.AnnaMarunko.cibinternstesttask.entities.Sock;
import com.AnnaMarunko.cibinternstesttask.services.SockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * The type Sock controller.
 */
@RestController
public class SockController {

    private final SockService sockService;

    /**
     * Instantiates a new Sock controller.
     *
     * @param sockService the sock service
     */
    @Autowired
    public SockController(SockService sockService){
        this.sockService = sockService;
    }


    /**
     * Income response entity.
     *
     * @param sock the sock
     * @return the response entity
     */
    @PostMapping("/api/socks/income")
    public ResponseEntity<?> income(@RequestBody Sock sock){
        final List<Sock> optionalSock = sockService.findByColorAndEquals(sock.getColor(), sock.getCottonPart());
        if (sock.getColor() == null || sock.getQuantity() == null || sock.getCottonPart() == null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } else {
            if (optionalSock.isEmpty()){
                sockService.create(sock);
                return new ResponseEntity<>(HttpStatus.OK);
            } else {
                optionalSock.get(0).setQuantity(optionalSock.get(0).getQuantity()+sock.getQuantity());
                sockService.update(optionalSock.get(0));
                return new ResponseEntity<>(HttpStatus.OK);
        }}
    }


    /**
     * Outcome response entity.
     *
     * @param sock the sock
     * @return the response entity
     */
    @PostMapping("/api/socks/outcome")
    public ResponseEntity<?> outcome(@RequestBody Sock sock){
        final List<Sock> optionalSock = sockService.findByColorAndEquals(sock.getColor(), sock.getCottonPart());
        if (optionalSock.isEmpty()){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } else if (optionalSock.get(0).getQuantity() < sock.getQuantity()){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } else {
            optionalSock.get(0).setQuantity(optionalSock.get(0).getQuantity()-sock.getQuantity());
            sockService.update(optionalSock.get(0));
            return new ResponseEntity<>(HttpStatus.OK);
        }
    }


    /**
     * Find by params response entity.
     *
     * @param color      the color
     * @param operation  the operation
     * @param cottonPart the cotton part
     * @return the response entity
     */
    @GetMapping("/api/socks")
    @ResponseBody
    public ResponseEntity<?> findByParams(@RequestParam String color, @RequestParam(defaultValue = "equal")
            String operation, @RequestParam Integer cottonPart){
            final List<Sock> socks;
            switch (operation) {
                case "equal":
                    socks = sockService.findByColorAndEquals(color, cottonPart);
                    return new ResponseEntity<>("quantity: " + socks.stream().mapToInt(Sock::getQuantity).sum(), HttpStatus.OK);
                case "moreThan":
                    socks = sockService.findByColorAndMoreThan(color, cottonPart);
                    return new ResponseEntity<>("quantity: " + socks.stream().mapToInt(Sock::getQuantity).sum(), HttpStatus.OK);
                case "lessThan":
                    socks = sockService.findByColorAndLessThan(color, cottonPart);
                    return new ResponseEntity<>("quantity: " + socks.stream().mapToInt(Sock::getQuantity).sum(), HttpStatus.OK);
                default:
                    return new ResponseEntity<>(HttpStatus.BAD_REQUEST);}
    }


    /**
     * Find all response entity.
     *
     * @return the response entity
     */
    @GetMapping("/api/socks/all")
    public ResponseEntity<List<Sock>> findAll(){
        final List<Sock> socks = sockService.findAll();
        return socks != null && !socks.isEmpty()
                ? new ResponseEntity<>(socks, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }


    /**
     * Find response entity.
     *
     * @param id the id
     * @return the response entity
     */
    @GetMapping("/api/socks/{id}")
    public ResponseEntity<Optional<Sock>> find(@PathVariable(name = "id") Long id){
        final Optional<Sock> sock = sockService.find(id);
        return sock != null
                ? new ResponseEntity<>(sock, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }


}
