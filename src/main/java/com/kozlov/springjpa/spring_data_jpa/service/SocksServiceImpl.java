package com.kozlov.springjpa.spring_data_jpa.service;


import com.kozlov.springjpa.spring_data_jpa.Operations;
import com.kozlov.springjpa.spring_data_jpa.dao.SocksRepository;
import com.kozlov.springjpa.spring_data_jpa.entity.Socks;
import com.kozlov.springjpa.spring_data_jpa.entity.SocksId;
import com.kozlov.springjpa.spring_data_jpa.exception.EmptySocksStorageException;
import com.kozlov.springjpa.spring_data_jpa.exception.NegativeSocksQuantityException;
import com.kozlov.springjpa.spring_data_jpa.exception.NoSuchSocksInDatabaseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SocksServiceImpl implements SocksService{

    private SocksRepository socksRepository;

    @Autowired
    public SocksServiceImpl(SocksRepository socksRepository) {
        this.socksRepository = socksRepository;
    }

    @Override
    public void setIncomeSocks(Socks socks) {
        Socks receivedSocks;

        Optional<Socks> optional = socksRepository.findById(new SocksId(socks.getColor(), socks.getCottonPart()));

        if(optional.isPresent()) {
            receivedSocks = optional.get();
            receivedSocks.setQuantity(receivedSocks.getQuantity() + socks.getQuantity());

            socksRepository.save(receivedSocks);
        } else {
            socksRepository.save(socks);
        }

    }

    @Override
    public void setOutcomeSocks(Socks socks) {
        Socks receivedSocks;

        Optional<Socks> optional = socksRepository.findById(new SocksId(socks.getColor(), socks.getCottonPart()));

        if(optional.isPresent()) {
            receivedSocks = optional.get();

            if(receivedSocks.getQuantity() >= socks.getQuantity()) {
                receivedSocks.setQuantity(receivedSocks.getQuantity() - socks.getQuantity());

                socksRepository.save(receivedSocks);
            } else {
                throw new NegativeSocksQuantityException();
            }
        } else {
            throw new NoSuchSocksInDatabaseException();
        }
    }

    @Override
    public int getSocksQuantity(String color, String operation, int cottonPart) {
        int count = 0;

        List<Socks> socksList = null;

        Operations op = Operations.valueOf(operation.toUpperCase());

        switch (op) {
            case MORETHAN:
                socksList = socksRepository
                        .findSocksByColorAndCottonPartIsGreaterThan(color,cottonPart);
                break;
            case LESSTHAN:
                socksList = socksRepository
                        .findSocksByColorAndCottonPartIsLessThan(color,cottonPart);
                break;
            case EQUAL:
                socksList = socksRepository
                        .findSocksByColorAndCottonPartIs(color,cottonPart);
                break;
        }
        if(socksList == null) {
            throw new EmptySocksStorageException();
        } else {
            for (Socks s : socksList) {
                count += s.getQuantity();
            }
        }

        return count;
    }
}
