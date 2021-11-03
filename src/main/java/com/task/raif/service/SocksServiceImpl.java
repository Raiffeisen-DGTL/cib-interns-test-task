package com.task.raif.service;

import com.task.raif.dto.SocksDto;
import com.task.raif.exception.InvalidQuantityException;
import com.task.raif.exception.NotFoundException;
import com.task.raif.model.Socks;
import com.task.raif.repository.SocksRepository;
import org.springframework.stereotype.Service;

@Service
public class SocksServiceImpl implements SocksService {
    SocksRepository socksRepository;

    public SocksServiceImpl(SocksRepository socksRepository) {
        this.socksRepository = socksRepository;
    }


    @Override
    public int getSocksByParams(String color, String operation, int cottonPart) {
        if (operation.equals("moreThan")) {
            return socksRepository.findSocksModelsByColorAndCottonPartGreaterThan(color, cottonPart)
                    .stream().mapToInt(Socks::getQuantity).sum();
        } else if (operation.equals("equal")) {
            return socksRepository.findSocksModelsByColorAndCottonPartEquals(color, cottonPart)
                    .stream().mapToInt(Socks::getQuantity).sum();
        } else {
            return socksRepository.findSocksModelsByColorAndCottonPartLessThan(color, cottonPart)
                    .stream().mapToInt(Socks::getQuantity).sum();
        }
    }

    @Override
    public void income(SocksDto socksDTO) {
        Socks incomeSocks = socksRepository
                .findSocksModelsByColorAndCottonPart(socksDTO.getColor(), socksDTO.getCottonPart())
                .orElse(new Socks(socksDTO.getColor(), socksDTO.getCottonPart(), socksDTO.getQuantity()));
        if (incomeSocks.getId() != null) {
            incomeSocks.setQuantity(incomeSocks.getQuantity() + socksDTO.getQuantity());
        }
        socksRepository.save(incomeSocks);
    }

    @Override
    public void outcome(SocksDto socksDTO) {
        Socks outcomeSocks = socksRepository
                .findSocksModelsByColorAndCottonPart(socksDTO.getColor(), socksDTO.getCottonPart())
                .orElseThrow(() -> new NotFoundException("Данный товар отсутствует"));
        if (outcomeSocks.getQuantity() < socksDTO.getQuantity()) {
            throw new InvalidQuantityException("Недостаточное количество товара для отпуска");
        }
        outcomeSocks.setQuantity(outcomeSocks.getQuantity() - socksDTO.getQuantity());
        socksRepository.save(outcomeSocks);
    }
}
