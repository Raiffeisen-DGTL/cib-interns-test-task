package com.raiffeisendgtl.ApiSocks.services;

import com.raiffeisendgtl.ApiSocks.components.Operation;
import com.raiffeisendgtl.ApiSocks.components.SocksErrorCode;
import com.raiffeisendgtl.ApiSocks.components.SocksException;
import com.raiffeisendgtl.ApiSocks.entities.Socks;
import com.raiffeisendgtl.ApiSocks.repositories.SocksRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class SocksService {
    @Autowired
    private SocksRepository socksRepository;

    public void income(Socks socks) {
        Optional<Socks> currentSocks = find(socks);

        if (!currentSocks.isPresent()) {
            currentSocks = Optional.of(socks);
        }
        else {
            currentSocks.get().addQuantity(socks.getQuantity());
        }

        save(currentSocks.get());
    }

    public void outcome(Socks socks) {
        Optional<Socks> currentSocks = find(socks);

        try {
            currentSocks.get().subtractQuantity(socks.getQuantity());
        }
        catch (NoSuchElementException e) {
            throw new SocksException(SocksErrorCode.INCORRECT_PARAMS);
        }

        save(currentSocks.get());
    }

    public Integer getCountSocks(String color, String operation, int cottonPart) {
        Operation currentOperation = Operation.convertFromString(operation);

        Integer count = 0;

        try {
            if (currentOperation == Operation.lessThan) {
                count = socksRepository.findCountSocksLessThan(color, cottonPart);
            }
            if (currentOperation == Operation.equal) {
                count = socksRepository.findCountSocksEqual(color, cottonPart);
            }
            if (currentOperation == Operation.moreThan) {
                count = socksRepository.findCountSocksMoreThan(color, cottonPart);
            }
        }
        catch (Throwable e) {
            throw new SocksException(SocksErrorCode.SERVER_CRASH);
        }

        if (count == null) {
            throw new SocksException(SocksErrorCode.INCORRECT_PARAMS);
        }

        return count;
    }

    private Optional<Socks> find(Socks socks) {
        try {
            return socksRepository.findByColorAndCottonPart(socks.getColor(), socks.getCottonPart());
        }
        catch (Throwable e) {
            throw new SocksException(SocksErrorCode.SERVER_CRASH);
        }
    }

    private void save(Socks socks) {
        try {
            socksRepository.save(socks);
        }
        catch (Throwable e) {
            throw new SocksException(SocksErrorCode.SERVER_CRASH);
        }
    }

}
