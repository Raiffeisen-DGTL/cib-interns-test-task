package raif.test.socks.controllers;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Repository;
import org.springframework.util.MultiValueMap;
import raif.test.socks.model.Sock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import raif.test.socks.dao.SocksRepository;
import raif.test.socks.model.SockPK;

import javax.validation.Valid;
import java.awt.*;
import java.security.Principal;
import java.util.ArrayList;
import java.util.Optional;

@RestController
@RequestMapping("api/socks")
public class SocksController {
    private final SocksRepository socksRepository;

    @Autowired
    public SocksController(SocksRepository socksRepository) {
        this.socksRepository = socksRepository;
    }

    @RequestMapping(value = "/income", method = RequestMethod.POST)
    public ResponseEntity<?> add(@Valid @RequestBody Sock sock) {
        Optional<Sock> opSock = socksRepository.findByColorAndCottonPart(sock.getColor(), sock.getCottonPart());
        if (opSock.isPresent()) {
            Sock existSock = opSock.get();
            sock.setQuantity(existSock.getQuantity() + sock.getQuantity());
        }
        socksRepository.save(sock);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(value = "/outcome", method = RequestMethod.POST)
    public ResponseEntity<?> reduce(@RequestBody Sock sock) {
        Optional<Sock> opSock = socksRepository.findByColorAndCottonPart(sock.getColor(), sock.getCottonPart());
        if (opSock.isPresent()) {
            Sock existSock = opSock.get();
            if (existSock.getQuantity() < sock.getQuantity()) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
            else if (existSock.getQuantity().equals(sock.getQuantity())) {
                socksRepository.delete(sock);
            }
            else {
                sock.setQuantity(existSock.getQuantity() - sock.getQuantity());
                socksRepository.save(sock);
            }
        }
        else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    public ResponseEntity<?> get(@RequestParam("color") String color,
                                 @RequestParam("operation") String operation,
                                 @RequestParam("cottonPack") Byte cottonPack) {
        switch (operation) {
            case "moreThan": {
                Iterable<Sock> socks = socksRepository.findAllByColorAndCottonPartGreaterThan(color, cottonPack);
                Integer quantity = 0;
                for (Sock sock : socks) {
                    quantity += sock.getQuantity();
                }
                return ResponseEntity.ok(quantity);
            }
            case "lessThan": {
                Iterable<Sock> socks = socksRepository.findAllByColorAndCottonPartLessThan(color, cottonPack);
                Integer quantity = 0;
                for (Sock sock : socks) {
                    quantity += sock.getQuantity();
                }
                return ResponseEntity.ok(quantity);
            }
            case "equal": {
                Optional<Sock> opSock = socksRepository.findByColorAndCottonPart(color, cottonPack);
                Integer quantity = 0;
                if (opSock.isPresent()) {
                    quantity = opSock.get().getQuantity();
                }
                return ResponseEntity.ok(quantity);
            }
            default:
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value="/get", method = RequestMethod.GET)
    public ResponseEntity<?> get() {
        return ResponseEntity.ok(socksRepository.findAll());
    }

}
