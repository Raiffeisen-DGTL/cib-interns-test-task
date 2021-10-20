package com.example.cibinternstesttask.services.impl;

import com.example.cibinternstesttask.io.entities.SockEntity;
import com.example.cibinternstesttask.io.repositories.SockRepository;
import com.example.cibinternstesttask.services.SockService;
import com.example.cibinternstesttask.ui.model.requests.SocksDetailsRequest;
import com.example.cibinternstesttask.ui.model.responses.SocksOperations;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class SockServiceImpl implements SockService {

    @Autowired
    SockRepository sockRepository;

    @Override
    public SockEntity incomeSocks(SocksDetailsRequest socksDetailsRequest) {
        SockEntity returnValue;

        SockEntity socks = sockRepository.findByColorAndCottonPart(socksDetailsRequest.getColor(), socksDetailsRequest.getCottonPart());
        if (socks == null) {
            ModelMapper modelMapper = new ModelMapper();
            returnValue = sockRepository.save(modelMapper.map(socksDetailsRequest, SockEntity.class));
        } else {
            socks.setQuantity(socks.getQuantity() + socksDetailsRequest.getQuantity());
            returnValue = sockRepository.save(socks);
        }

        return returnValue;
    }

    @Override
    public SockEntity outcomeSocks(SocksDetailsRequest socksDetailsRequest) {
        SockEntity socks = sockRepository.findByColorAndCottonPart(socksDetailsRequest.getColor(), socksDetailsRequest.getCottonPart());
        if (socks == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Socks[color:" + socksDetailsRequest.getColor()
                    + ";cottonPart:" + socksDetailsRequest.getCottonPart() + "] not found");
        }

        if (socks.getQuantity() - socksDetailsRequest.getQuantity() < 0) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Socks quantity will be less than 0");
        }

        socks.setQuantity(socks.getQuantity() - socksDetailsRequest.getQuantity());
        return sockRepository.save(socks);
    }

    @Override
    public Long getSocksQuantity(String color, SocksOperations operation, int cottonPart) {
        List<SockEntity> socks = null;

        switch (operation) {
            case EQUAL:
                socks = sockRepository.findAllByColorAndCottonPartEquals(color, cottonPart);
                break;

            case LESS_THAN:
                socks = sockRepository.findAllByColorAndCottonPartLessThan(color, cottonPart);
                break;

            case MORE_THAN:
                socks = sockRepository.findAllByColorAndCottonPartGreaterThan(color, cottonPart);
                break;
        }

        return socks.stream().map(SockEntity::getQuantity).reduce(0L, Long::sum);
    }
}
