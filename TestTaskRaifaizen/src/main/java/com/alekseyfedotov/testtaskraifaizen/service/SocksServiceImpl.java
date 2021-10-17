package com.alekseyfedotov.testtaskraifaizen.service;

import com.alekseyfedotov.testtaskraifaizen.entity.Socks;
import com.alekseyfedotov.testtaskraifaizen.repository.SocksRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class SocksServiceImpl implements SocksService {

    @Autowired
    private SocksRepository socksRepository;

    @Override
    public ResponseEntity addSocks(Socks socks) {
        if (socks.getColor() == null || socks.getCottonPart() == null || socks.getQuantity() == null)
            return new ResponseEntity(HttpStatus.BAD_REQUEST);

        Optional<Socks> socksOptional = socksRepository
                .findSocksByColorAndCottonPart(socks.getColor(), socks.getCottonPart());

        //check this socks in DB
        Socks socksInDB = null;
        if (socksOptional.isPresent()) {
            socksInDB = socksOptional.get();
            int oldQuantity = socksInDB.getQuantity();
            socksInDB.setQuantity(oldQuantity + socks.getQuantity());
        } else {
            socksInDB = new Socks(socks.getColor(), socks.getCottonPart(), socks.getQuantity());
        }
        socksRepository.save(socksInDB);

        //check that saved
        socksOptional = socksRepository.findById(socksInDB.getId());
        if (socksOptional.isPresent() && socksOptional.get().getQuantity() == socksInDB.getQuantity())
            return new ResponseEntity(HttpStatus.OK);
        else
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
    }


    @Override
    public ResponseEntity removeSocks(Socks socksToRemove) {
        //check input correct
        if (socksToRemove.getColor() == null || socksToRemove.getCottonPart() == null
                || socksToRemove.getQuantity() == null)
            return new ResponseEntity(HttpStatus.BAD_REQUEST);

        //check this socks in DB
        Optional<Socks> socksOptional = socksRepository
                .findSocksByColorAndCottonPart(socksToRemove.getColor(), socksToRemove.getCottonPart());
        Socks socksInDB = null;
        if (socksOptional.isPresent()) {
            socksInDB = socksOptional.get();
            if (socksToRemove.getQuantity() > socksInDB.getQuantity())
                return new ResponseEntity(HttpStatus.BAD_REQUEST);
        } else {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }

        //save to DB
        int oldQuantity = socksInDB.getQuantity();
        socksInDB.setQuantity(oldQuantity - socksToRemove.getQuantity());
        socksRepository.save(socksInDB);

        //check that saved
        socksOptional = socksRepository.findById(socksInDB.getId());
        if (socksOptional.isPresent() && socksOptional.get().getQuantity() == socksInDB.getQuantity())
            return new ResponseEntity(HttpStatus.OK);
        else
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);

    }

    @Override
    public Integer getCountOfSocks(String color, String operation, int cottonPart) {
        List<Socks> socksList = socksRepository.findSocksByColor(color);
        int res = 0;
        List<Integer> quantities=null;
        switch (operation) {
            case "moreThan":
                quantities = socksList.stream()
                        .filter(socks -> socks.getCottonPart() > cottonPart)
                        .map(Socks::getQuantity)
                        .collect(Collectors.toList());
                break;
            case "lessThan":
                quantities = socksList.stream()
                        .filter(socks -> socks.getCottonPart() < cottonPart)
                        .map(Socks::getQuantity)
                        .collect(Collectors.toList());
                break;
            default:
                quantities = socksList.stream()
                        .filter(socks -> socks.getCottonPart() == cottonPart)
                        .map(Socks::getQuantity)
                        .collect(Collectors.toList());
                break;
        }

        for (Integer a : quantities)
            res+=a;
        return res;
    }

    @Override
    public List<Socks> getAll() {
        return socksRepository.findAll();
    }
}
