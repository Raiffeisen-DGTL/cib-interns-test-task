package com.raiffeisen.raiffeisen_test_task.service;

import com.raiffeisen.raiffeisen_test_task.exception.RaiffeisenException;
import com.raiffeisen.raiffeisen_test_task.model.Operation;
import com.raiffeisen.raiffeisen_test_task.model.Socks;
import com.raiffeisen.raiffeisen_test_task.repository.SocksRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SocksServiceImpl implements SocksService {
    private final SocksRepository socksRepository;

    @Autowired
    public SocksServiceImpl(SocksRepository socksRepository) {
        this.socksRepository = socksRepository;
    }

    @Override
    public void addSocks(Socks socks) {
        SocksValidator.validateSocks(socks);

        if (socksRepository.getSocksByColorAndCottonPartEquals(socks.getColor(),
                socks.getCottonPart()).isEmpty()) {
            socksRepository.save(socks);
        } else {
            Socks DBSocks = socksRepository.getSocksByColorAndCottonPartEquals(socks.getColor(),
                    socks.getCottonPart()).get();
            DBSocks.setQuantity(DBSocks.getQuantity() + socks.getQuantity());
            socksRepository.save(DBSocks);
        }
    }

    @Override
    public void decreaseSocks(Socks socks) {
        SocksValidator.validateSocks(socks);

        if (socksRepository.getSocksByColorAndCottonPartEquals(socks.getColor(),
                socks.getCottonPart()).isPresent()) {
            Socks DBSocks = socksRepository.getSocksByColorAndCottonPartEquals(socks.getColor(),
                    socks.getCottonPart()).get();
            SocksValidator.validateQuantitiesBeforeSubtraction(DBSocks.getQuantity(), socks.getQuantity());
            DBSocks.setQuantity(DBSocks.getQuantity() - socks.getQuantity());
            socksRepository.save(DBSocks);
        }
    }

    @Override
    public Socks getSocks(String color, Operation operation, byte cottonPart) {
        SocksValidator.validateSocks(color, cottonPart);
        Optional<Socks> socks;
        switch (operation) {
            case lessThan:
                socks = socksRepository.getSocksByColorAndCottonPartLessThan(color, cottonPart);
                break;
            case equal:
                socks = socksRepository.getSocksByColorAndCottonPartEquals(color, cottonPart);
                break;
            case moreThan:
                socks = socksRepository.getSocksByColorAndCottonPartGreaterThan(color, cottonPart);
                break;
            default:
                throw new RaiffeisenException.IncorrectParameterException("Incorrect operation");
        }
        if (socks.isEmpty()) throw new RaiffeisenException.NullResultException("No results");
        return socks.get();
    }
}
