package com.example.socks.service;

import com.example.socks.Util.Operations;
import com.example.socks.db.dto.SocksDTO;
import com.example.socks.db.entity.Socks;
import com.example.socks.db.repository.SocksRepository;
import com.example.socks.db.repository.SocksRepositoryBySpecification;
import com.example.socks.db.repository.SocksSpecification;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static org.springframework.data.jpa.domain.Specification.where;

@Service
@RequiredArgsConstructor
public class SocksService {

    private final ModelMapper mapper;
    private final SocksRepositoryBySpecification repository;

    public long saveSocks(SocksDTO socksDTO) {
        var check = getSocks(socksDTO);
        if (check != null) {
            check.setQuantity(check.getQuantity() + socksDTO.getQuantity());
            return repository.save(check).getId();
        }
        return repository.save(mapper.map(socksDTO, Socks.class)).getId();
    }

    @Transactional
    public void outcome(Socks socks, SocksDTO socksDTO) {
        socks.setQuantity(socks.getQuantity() - socksDTO.getQuantity());
        repository.save(socks);
    }

    public Socks getSocks(SocksDTO socksDTO) {
        return repository.findOne(
                where(
                        SocksSpecification
                                .socksByCottonPart(socksDTO.getCottonPart())
                                .and(SocksSpecification.socksByColor(socksDTO.getColor()))
                )
        ).orElse(null);
    }

    public int getSocksByOperation(String color, Operations operation, int cottonPart) {

        switch (operation) {
            case MORETHAN:
                return repository
                        .findAll(
                             where(
                                   SocksSpecification.socksByColor(color)
                                           .and(SocksSpecification.cottonPartMoreThan(cottonPart))
                             )
                        )
                        .stream()
                        .mapToInt(Socks::getQuantity)
                        .sum();
            case LESSTHAN:
                return repository
                        .findAll(
                                where(
                                        SocksSpecification
                                                .socksByColor(color)
                                                .and(SocksSpecification.cottonPartLessThan(cottonPart))
                                )
                        )
                        .stream()
                        .mapToInt(Socks::getQuantity)
                        .sum();
            case EQUAL:
                return repository
                        .findAll(
                                where(
                                        SocksSpecification
                                                .socksByCottonPart(cottonPart)
                                                .and(SocksSpecification.socksByColor(color))
                                )
                        )
                        .stream()
                        .mapToInt(Socks::getQuantity)
                        .sum();
        }

        return 0;
    }
}
