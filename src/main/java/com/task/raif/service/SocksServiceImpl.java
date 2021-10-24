package com.task.raif.service;

import com.task.raif.model.Socks;
import com.task.raif.exception.InvalidQuantityException;
import com.task.raif.exception.NotFoundException;
import com.task.raif.model.SocksModel;
import com.task.raif.repository.SocksRepository;
import org.springframework.stereotype.Component;

@Component
public class SocksServiceImpl implements SocksService {
    SocksRepository socksRepository;

    public SocksServiceImpl(SocksRepository socksRepository) {
        this.socksRepository = socksRepository;
    }


    @Override
    public int getSocksByParams(String color, String operation, int cottonPart) {
        if (operation.equals("moreThan")) {
            return socksRepository.findSocksModelsByColorAndCottonPartGreaterThan(color, cottonPart)
                    .stream().mapToInt(SocksModel::getQuantity).sum();
        } else if (operation.equals("equal")) {
            return socksRepository.findSocksModelsByColorAndCottonPartEquals(color, cottonPart)
                    .stream().mapToInt(SocksModel::getQuantity).sum();
        } else {
            return socksRepository.findSocksModelsByColorAndCottonPartLessThan(color, cottonPart)
                    .stream().mapToInt(SocksModel::getQuantity).sum();
        }
    }

    @Override
    public void income(Socks socks) {
        SocksModel incomeSocks = socksRepository
                .findSocksModelsByColorAndCottonPart(socks.getColor(), socks.getCottonPart())
                .orElse(new SocksModel(socks.getColor(), socks.getCottonPart(), socks.getQuantity()));
        if (incomeSocks.getId() != null) {
            incomeSocks.setQuantity(incomeSocks.getQuantity() + socks.getQuantity());
        }
        socksRepository.save(incomeSocks);
    }

    @Override
    public void outcome(Socks socks) {
        SocksModel outcomeSocks = socksRepository
                .findSocksModelsByColorAndCottonPart(socks.getColor(), socks.getCottonPart())
                .orElseThrow(() -> new NotFoundException("Данный товар отсутствует"));
        if (outcomeSocks.getQuantity() < socks.getQuantity()) {
            throw new InvalidQuantityException("Недостаточное количество товара для отпуска");
        }
        outcomeSocks.setQuantity(outcomeSocks.getQuantity() - socks.getQuantity());
        socksRepository.save(outcomeSocks);
    }
}