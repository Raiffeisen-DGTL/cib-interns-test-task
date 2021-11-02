package com.example.demo.service;

import com.example.demo.entity.Socks;
import com.example.demo.exception.SocksQuantityException;
import com.example.demo.repository.SocksRepository;
import javassist.NotFoundException;
import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.naming.LimitExceededException;
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

    public void updateQuantityIncome(Socks socks) {

        try {
            Socks existingSocks = retrieveSocksFromDB(socks);
            existingSocks.setQuantity(existingSocks.getQuantity() + socks.getQuantity());
            addSocks(existingSocks);
        } catch (NotFoundException notFoundException) {
            addSocks(socks);
        }
    }

    public void updateQuantityOutcome(Socks socks) throws NotFoundException, SocksQuantityException {
        Socks existingSocks = retrieveSocksFromDB(socks);

        if (existingSocks.getQuantity() - socks.getQuantity() < 0) {
            throw new SocksQuantityException ("amount of requested socks exceeds the allowed value");
        } else {
            existingSocks.setQuantity(existingSocks.getQuantity() - socks.getQuantity());
            addSocks(existingSocks);
        }
    }

    public Socks retrieveSocksFromDB(Socks socks) throws NotFoundException {

        List<Socks> socksList = new ArrayList<>(socksRepository.findByColorAndCottonPart(socks.getColor(), socks.getCottonPart()));

        if (socksList.isEmpty()) {
            throw new NotFoundException("Entity not found in DB");
        } else {
            return socksList.get(0);
        }
    }

    public String countSocksWithParams(String color, int cottonPart, String operation) {
        int quantity = 0;
        switch (operation.toLowerCase().trim()) {
            case ("equal"):
                if (socksRepository.existsByColorAndCottonPart(color, cottonPart)) {
                    List<Socks> socksListEqual = new ArrayList<>(socksRepository.findByColorAndCottonPart(color, cottonPart));
                    return String.valueOf(socksListEqual.get(0).getQuantity());
                } else {
                    return null;
                }
            case ("morethan"):
                List<Socks> socksListMoreThan = new ArrayList<>(socksRepository.findAllByColorAndCottonPartGreaterThan(color, cottonPart));
                if (socksListMoreThan.isEmpty()) {
                    return null;
                } else {
                    for (Socks sock : socksListMoreThan) {
                        quantity += sock.getQuantity();
                    }
                    return String.valueOf(quantity);
                }
            case("lessthan"):
                List<Socks> socksListLessThan = new ArrayList<>(socksRepository.findAllByColorAndCottonPartLessThan(color, cottonPart));
                if (socksListLessThan.isEmpty()) {
                    return null;
                } else {
                    for (Socks sock : socksListLessThan) {
                        quantity += sock.getQuantity();
                    }
                    return String.valueOf(quantity);
                }
            default:
                return "wrong operation type. Use 'equal', 'lessThan' or 'moreThan'";
        }
    }
}
