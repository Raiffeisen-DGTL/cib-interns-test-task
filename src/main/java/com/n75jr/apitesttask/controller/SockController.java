package com.n75jr.apitesttask.controller;

import com.n75jr.apitesttask.dao.SockRepository;
import com.n75jr.apitesttask.model.Sock;
import com.n75jr.apitesttask.model.SockID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class SockController {
    @Autowired
    private SockRepository sockRepository;

    // helper methods:
    private boolean isValidSock(Sock sock) {
        boolean result = true;
        if (sock.getColor() == null || sock.getCottonPart() == 0 || sock.getQuantity() == 0) {
            return false;
        }
        if (sock.getCottonPart() <= 0 || sock.getCottonPart() > 100 || sock.getQuantity() <= 0) {
            return false;
        }
        return result;
    }
    //

    @PostMapping("/socks/income")
    public ResponseEntity<Sock> income(@RequestBody Sock sock) {
        if (!isValidSock(sock)) {
            return new ResponseEntity<>(sock, HttpStatus.BAD_REQUEST);
        }
        SockID id = new SockID(sock.getColor(), sock.getCottonPart());
        if (!sockRepository.existsById(id)) {
            sockRepository.save(sock);
            return new ResponseEntity<>(sock, HttpStatus.OK);
        }
        Sock existSock = sockRepository.getById(id);
        sock.setQuantity(existSock.getQuantity() + sock.getQuantity());
        sockRepository.save(sock);
        return new ResponseEntity<>(sock, HttpStatus.OK);
    }
}
