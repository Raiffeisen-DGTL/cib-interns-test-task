package com.example.demo.service;

import com.example.demo.entity.Socks;
import com.example.demo.repository.SocksRepository;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Component
@Service
public class SocksService {

    @Autowired
    SocksRepository socksRepository;

    public void addSocks(Socks socks){
        socksRepository.save(socks);
    }

    public Boolean isAlreadyExist(Socks socks) {
        return socksRepository.existsByColorAndCottonPart(socks.getColor(), socks.getCottonPart());
    }

    public void updateQuantityIncome(Socks socks) throws NotFoundException {
        Socks existingSocks = retrieveSocksFromDB(socks);
        existingSocks.setQuantity(existingSocks.getQuantity() + socks.getQuantity());
        addSocks(existingSocks);
    }

    public void updateQuantityOutcome(Socks socks) throws NotFoundException {
        Socks existingSocks = retrieveSocksFromDB(socks);
        existingSocks.setQuantity(existingSocks.getQuantity() - socks.getQuantity());
        addSocks(existingSocks);
    }

    public Boolean isEnoughQuantity(Socks socks) throws NotFoundException {
        Socks existingSocks = retrieveSocksFromDB(socks);
        return existingSocks.getQuantity() >= socks.getQuantity();
    }

    public Socks retrieveSocksFromDB(Socks socks) throws NotFoundException {
        if (isAlreadyExist(socks)) {
            List<Socks> socksList = new ArrayList<>(socksRepository.findByColorAndCottonPart(socks.getColor(), socks.getCottonPart()));
            return socksList.get(0);
        } else {
            throw new NotFoundException("no such Entity in database");
        }
    }

    public ResponseEntity<String> countSocksWithParams(String color, int cottonPart, String operation) {
        int quantity = 0;
        switch (operation.toLowerCase().trim()) {
            case ("equal"):
                if (socksRepository.existsByColorAndCottonPart(color, cottonPart)) {
                    List<Socks> socksListEqual = new ArrayList<>(socksRepository.findByColorAndCottonPart(color, cottonPart));
                    return new ResponseEntity<>(Integer.toString(socksListEqual.get(0).getQuantity()), HttpStatus.OK);
                } else {
                    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
                }
            case ("morethan"):
                List<Socks> socksListMoreThan = new ArrayList<>(socksRepository.findAllByColorAndCottonPartGreaterThan(color, cottonPart));
                if (socksListMoreThan.isEmpty()) {
                    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
                } else {
                    for (Socks sock : socksListMoreThan) {
                        quantity += sock.getQuantity();
                    }
                    return new ResponseEntity<>(Integer.toString(quantity), HttpStatus.OK);
                }
            case("lessthan"):
                List<Socks> socksListLessThan = new ArrayList<>(socksRepository.findAllByColorAndCottonPartLessThan(color, cottonPart));
                if (socksListLessThan.isEmpty()) {
                    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
                } else {
                    for (Socks sock : socksListLessThan) {
                        quantity += sock.getQuantity();
                    }
                    return new ResponseEntity<>(Integer.toString(quantity), HttpStatus.OK);
                }
            default:
                return new ResponseEntity<>("wrong operation type. Use 'equal', 'lessThan' or 'moreThan'", HttpStatus.BAD_REQUEST);
        }
    }
}
