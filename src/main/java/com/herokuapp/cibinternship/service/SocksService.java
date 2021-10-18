package com.herokuapp.cibinternship.service;

import com.herokuapp.cibinternship.exception.NotEnoughSocksException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.herokuapp.cibinternship.repository.SocksRepository;
import com.herokuapp.cibinternship.model.Socks;
import com.herokuapp.cibinternship.model.SocksId;

import javax.persistence.EntityNotFoundException;
import java.util.Optional;

@Service
public class SocksService {
    private final SocksRepository socksRepository;

    @Autowired
    public SocksService(SocksRepository socksRepository) {
        this.socksRepository = socksRepository;
    }

    public void registerSocksIncome(Socks socks) {
        SocksId id = new SocksId(socks.getColor(), socks.getCottonPart());
        Optional<Socks> socksOptional = socksRepository.findById(id);

        if (socksOptional.isPresent()) {
            Socks socksFound = socksOptional.get();
            socksFound.setQuantity(socks.getQuantity() + socksFound.getQuantity());
            socksRepository.save(socksFound);
        }
        else socksRepository.save(socks);
    }


    public void registerSocksOutcome(Socks socks) {
        SocksId id = new SocksId(socks.getColor(), socks.getCottonPart()) ;
        Optional<Socks> socksOptional = socksRepository.findById(id);

        if (socksOptional.isPresent()) {
            Socks socksFound = socksOptional.get();

            if (socks.getQuantity() < socksFound.getQuantity()) {
                socksFound.setQuantity(socksFound.getQuantity() - socks.getQuantity());
                socksRepository.save(socksFound);
            }
            else
                throw new NotEnoughSocksException(String.format(
                    "There is less than '%d' of such socks in stock.",
                    socks.getQuantity()));
        }
        else
            throw new EntityNotFoundException(String.format(
                    "There is no '%s' socks with '%d' cottonPart in stock.",
                    socks.getColor(), socks.getQuantity()));
    }

    public long getSocksQuantity(String color, String operation, int cottonPart) {
        Optional<Integer> res = Optional.empty();

        switch (operation.toLowerCase()) {
            case ("lessthan"):
                res = socksRepository.sumQuantityByColorAndCottonPartLessThan(color, cottonPart);
                break;
            case ("morethan"):
                res = socksRepository.sumQuantityByColorAndCottonPartGreaterThan(color, cottonPart);
                break;
            case ("equal"):
                res = socksRepository.sumQuantityByColorAndCottonPartEquals(color, cottonPart);
                break;
        }

        return res.orElse(0);
    }
}
