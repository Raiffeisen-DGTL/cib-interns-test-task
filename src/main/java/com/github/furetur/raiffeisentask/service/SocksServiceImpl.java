package com.github.furetur.raiffeisentask.service;

import com.github.furetur.raiffeisentask.db.SocksRepository;
import com.github.furetur.raiffeisentask.exceptions.NotEnoughSocksException;
import com.github.furetur.raiffeisentask.restData.OperationType;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class SocksServiceImpl implements SocksService {

    private final SocksRepository socksRepository;

    public SocksServiceImpl(SocksRepository socksRepository) {
        this.socksRepository = socksRepository;
    }

    @Override
    public int countSocks(String color, OperationType operation, int cottonPart) {
        return switch (operation) {
            case EQUAL -> socksRepository.countByColorAndCottonPart(color, cottonPart);
            case LESS_THAN -> socksRepository.countByColorAndCottonPartLessThan(color, cottonPart);
            case MORE_THAN -> socksRepository.countByColorAndCottonPartGreaterThan(color, cottonPart);
        };
    }

    @Override
    @Transactional
    public void addSocks(String color, int cottonPart, int quantity) {
        socksRepository.addSocks(color, cottonPart, quantity);
    }

    @Override
    @Transactional
    public void removeSocks(String color, int cottonPart, int quantity) {
        var socks = socksRepository.findByColorAndCottonPart(color, cottonPart);
        if (socks != null && socks.getQuantity() >= quantity) {
            socks.setQuantity(socks.getQuantity() - quantity);
            socksRepository.save(socks);
        } else {
            throw new NotEnoughSocksException();
        }
    }
}
