package ru.raif.socks.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import ru.raif.socks.entity.Socks;
import ru.raif.socks.exception.InsufficientQuantityException;
import ru.raif.socks.exception.NotFoundException;
import ru.raif.socks.repository.SocksRepository;
import ru.raif.socks.request.Comparison;
import ru.raif.socks.request.SocksIncomeOutcomeRq;

import java.util.Objects;

@Component
public class SocksServiceImpl implements SocksService {

    private final SocksRepository socksRepository;

    @Autowired
    public SocksServiceImpl(SocksRepository socksRepository) {
        this.socksRepository = socksRepository;
    }

    @Override
    public void income(SocksIncomeOutcomeRq request) {
        Socks toSave = socksRepository
                .findSocksByColorAndCottonPart(request.getColor(), request.getCottonPart())
                .orElse(new Socks(request.getColor(), request.getCottonPart(), request.getQuantity()));
        if (toSave.getId() != null) {
            toSave.setQuantity(toSave.getQuantity() + request.getQuantity());
        }
        socksRepository.save(toSave);
    }

    @Override
    public void outcome(SocksIncomeOutcomeRq request) {
        Socks toSave = socksRepository
                .findSocksByColorAndCottonPart(request.getColor(), request.getCottonPart())
                .orElseThrow(() -> new NotFoundException("В базе данных отсутствует данный товар"));
        if (toSave.getQuantity() < request.getQuantity()) {
            throw new InsufficientQuantityException("В базе данных недостаточное количество для отражения расхода");
        }
        toSave.setQuantity(toSave.getQuantity() - request.getQuantity());
        socksRepository.save(toSave);
    }

    @Override
    @Transactional
    public Integer search(String color, Comparison operation, Integer cottonPart) {
        int result = 0;
        switch (operation) {
            case equal: {
                result = socksRepository.streamSocksByColor(color)
                        .filter(socks -> Objects.equals(socks.getCottonPart(), cottonPart))
                        .mapToInt(Socks::getQuantity).sum();
                break;
            }
            case lessThan: {
                result = socksRepository.streamSocksByColor(color)
                        .filter(socks -> socks.getCottonPart() < cottonPart)
                        .mapToInt(Socks::getQuantity).sum();
                break;
            }
            case moreThan: {
                result = socksRepository.streamSocksByColor(color)
                        .filter(socks -> socks.getCottonPart() > cottonPart)
                        .mapToInt(Socks::getQuantity).sum();
            }
        }
        return result;
    }
}
