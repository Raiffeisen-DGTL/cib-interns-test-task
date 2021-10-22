package com.example.socks2.service;

import com.example.socks2.dto.SocksFilterParams;
import com.example.socks2.entity.SocksEntity;
import com.example.socks2.exception.InvalidArgumentException;
import com.example.socks2.exception.SocksNotFoundException;
import com.example.socks2.repository.SocksRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.util.Collections.emptyList;
import static org.apache.commons.lang3.StringUtils.isBlank;

@Service
public class SocksService {

    private final SocksRepository socksRepo;

    @Autowired
    public SocksService(SocksRepository socksRepo) {
        this.socksRepo = socksRepo;
    }

    public void arrivalSocks(SocksEntity sock) {
        validateArguments(sock);

        socksRepo.findByColorAndCottonPart(sock.getColor(), sock.getCottonPart()).ifPresentOrElse(
                socksEntity -> {
                    socksEntity.addQuantity(sock.getQuantity());
                    socksRepo.save(socksEntity);
                },
                () -> socksRepo.save(sock)
        );
    }

    public void departureSocks(SocksEntity sock) {
        validateArguments(sock);

        socksRepo.findByColorAndCottonPart(sock.getColor(), sock.getCottonPart()).ifPresentOrElse(
                socksEntity -> {
                    if (socksEntity.getQuantity() < sock.getQuantity()) {
                        throw new InvalidArgumentException();
                    }

                    socksEntity.subtractQuantity(sock.getQuantity());
                    socksRepo.save(socksEntity);
                },
                () -> {
                    throw new SocksNotFoundException();
                }
        );
    }

    private static void validateArguments(SocksEntity sock){
        if (isBlank(sock.getColor()) || sock.getCottonPart() < 0 || sock.getCottonPart() > 100 || sock.getQuantity() <= 0) {
            throw new InvalidArgumentException();
        }
    }

    public Long getSocks(SocksFilterParams filterParams) {
        List<SocksEntity> list = null;

        switch (filterParams.getOperationType()) {
            case MORE_THAN -> list = socksRepo.findAllByColorAndCottonPartIsGreaterThan(
                    filterParams.getColor(),
                    filterParams.getCottonPart()
            );
            case LESS_THAN -> list = socksRepo.findAllByColorAndCottonPartIsLessThan(
                    filterParams.getColor(),
                    filterParams.getCottonPart());
            case EQUAL -> list = socksRepo.findByColorAndCottonPart(
                    filterParams.getColor(),
                    filterParams.getCottonPart()
            )
                    .map(List::of)
                    .orElse(emptyList());
        }

        return list.stream().mapToLong(SocksEntity::getQuantity).sum();
    }
}
