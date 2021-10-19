package com.example.socksservice.service;

import com.example.socksservice.dto.CountOperation;
import com.example.socksservice.entity.Sock;
import com.example.socksservice.exceptions.NotFoundSockException;
import com.example.socksservice.exceptions.NotSufficientStockException;
import com.example.socksservice.repository.SocksRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class SocksServiceImpl implements SocksService {
    private static final Logger logger = LoggerFactory.getLogger(SocksServiceImpl.class);

    private final SocksRepository repository;

    public SocksServiceImpl(SocksRepository repository) {
        this.repository = repository;
    }

    @Override
    public Optional<Sock> getByColorAndCotton(String color, int cotton) {
        return repository.findByColorAndCottonPart(color, cotton);
    }

    @Override
    public Sock save(Sock sock) {
        return repository.save(sock);
    }

    @Override
    @Transactional
    public Sock increaseQuantity(Sock sock, int qty) {
        Optional<Sock> sockOptional = getByColorAndCotton(sock.getColor(), sock.getCottonPart());
        if (sockOptional.isPresent()) {
            Sock exists = sockOptional.get();
            exists.setQuantity(exists.getQuantity() + qty);
            return exists;

        } else {
            save(sock);
            return sock;
        }
    }

    @Override
    @Transactional
    public Sock decreaseQuantity(Sock sock, int qty) {
        Optional<Sock> sockOptional = getByColorAndCotton(sock.getColor(), sock.getCottonPart());

        Sock exists = sockOptional.orElseThrow(NotFoundSockException::new);
        if (exists.getQuantity() < qty) {
            throw new NotSufficientStockException();
        }
        exists.setQuantity(exists.getQuantity() - qty);

        return exists;
    }

    @Override
    public Long getSocksCount(String color, int cotton, CountOperation operation) {
        switch (operation) {
            case EQUAL:
                return repository.countByColorAndAndCottonEq(color, cotton);
            case LESS_THAN:
                return repository.countByColorAndAndCottonLsTh(color, cotton);
            case MORE_THAN:
                return repository.countByColorAndAndCottonGtTh(color, cotton);
        }
        return 0L;
    }
}
