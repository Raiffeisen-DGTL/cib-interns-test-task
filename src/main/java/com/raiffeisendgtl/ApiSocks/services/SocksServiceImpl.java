package com.raiffeisendgtl.ApiSocks.services;

import com.raiffeisendgtl.ApiSocks.components.FinderOperation;
import com.raiffeisendgtl.ApiSocks.components.exception.SocksErrorCode;
import com.raiffeisendgtl.ApiSocks.components.exception.SocksException;
import com.raiffeisendgtl.ApiSocks.entities.Socks;
import com.raiffeisendgtl.ApiSocks.repositories.SocksRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@Transactional
public class SocksServiceImpl implements SocksService {

    private final SocksRepository socksRepository;

    @Autowired
    public SocksServiceImpl(SocksRepository socksRepository) {
        this.socksRepository = socksRepository;
    }

    @Override
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

    @Override
    public void outcome(Socks socks) {
        Optional<Socks> currentSocks = find(socks);

        try {
            currentSocks.get().subtractQuantity(socks.getQuantity());
        } catch (NoSuchElementException e) {
            throw new SocksException(SocksErrorCode.INCORRECT_PARAMS);
        }

        save(currentSocks.get());
    }

    @Override
    public Integer getCountSocks(String color, FinderOperation operation, int cottonPart) {
        Integer count = 0;

        try {
            operation.setSocksRepository(socksRepository);
            count = operation.findCount(color, cottonPart);
        } catch (Throwable e) {
            throw new SocksException(SocksErrorCode.SERVER_CRASH);
        }

        if (count == null) {
            throw new SocksException(SocksErrorCode.INCORRECT_PARAMS);
        }

        return count;
    }

    @Override
    public Optional<Socks> find(Socks socks) {
        try {
            return socksRepository.findByColorAndCottonPart(socks.getColor(), socks.getCottonPart());
        } catch (Throwable e) {
            throw new SocksException(SocksErrorCode.SERVER_CRASH);
        }
    }

    @Override
    public void save(Socks socks) {
        try {
            socksRepository.save(socks);
        } catch (Throwable e) {
            throw new SocksException(SocksErrorCode.SERVER_CRASH);
        }
    }

}
