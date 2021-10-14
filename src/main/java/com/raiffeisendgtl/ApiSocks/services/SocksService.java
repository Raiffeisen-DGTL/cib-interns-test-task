package com.raiffeisendgtl.ApiSocks.services;

import com.raiffeisendgtl.ApiSocks.components.Operation;
import com.raiffeisendgtl.ApiSocks.components.SocksErrorCode;
import com.raiffeisendgtl.ApiSocks.components.SocksException;
import com.raiffeisendgtl.ApiSocks.entities.Socks;
import com.raiffeisendgtl.ApiSocks.repositories.SocksRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SocksService {
    @Autowired
    private SocksRepository socksRepository;

    public void income(Socks socks) {
        Socks currentSocks = find(socks);

        if (currentSocks == null) {
            currentSocks = socks;
        }
        else {
            currentSocks.addQuantity(socks.getQuantity());
        }

        save(currentSocks);
    }

    public void outcome(Socks socks) {
        Socks currentSocks = find(socks);

        if (currentSocks == null) {
            throw new SocksException(SocksErrorCode.INCORRECT_PARAMS);
        }
        else  {
            currentSocks.subtractQuantity(socks.getQuantity());
        }

        save(currentSocks);
    }

    public Integer getCountSocks(String color, String operation, int cottonPart) {
        Operation currentOperation = Operation.convertFromString(operation);

        Integer count = 0;

        if (currentOperation == Operation.lessThan) {
            count = socksRepository.findCountSocksLessThan(color, cottonPart);
        }
        if (currentOperation == Operation.equal) {
            count = socksRepository.findCountSocksEqual(color, cottonPart);
        }
        if (currentOperation == Operation.moreThan) {
            count = socksRepository.findCountSocksMoreThan(color, cottonPart);
        }

        if (count == null) {
            throw new SocksException(SocksErrorCode.INCORRECT_PARAMS);
        }

        return count;
    }

    private Socks find(Socks socks) {
        Socks result;
        try {
            result = socksRepository.findByColorAndCottonPart(socks.getColor(), socks.getCottonPart());
        }
        catch (Throwable e) {
            throw new SocksException(SocksErrorCode.SERVER_CRASH);
        }
        return result;
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
