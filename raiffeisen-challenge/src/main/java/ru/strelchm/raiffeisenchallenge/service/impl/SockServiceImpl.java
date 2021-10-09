package ru.strelchm.raiffeisenchallenge.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import ru.strelchm.raiffeisenchallenge.domain.Sock;
import ru.strelchm.raiffeisenchallenge.dto.InOutComeSockDto;
import ru.strelchm.raiffeisenchallenge.dto.SockCriteria;
import ru.strelchm.raiffeisenchallenge.exception.BadRequestException;
import ru.strelchm.raiffeisenchallenge.repo.SockRepository;
import ru.strelchm.raiffeisenchallenge.service.SockService;

import java.util.List;

@Service
public class SockServiceImpl implements SockService {
    private final SockRepository sockRepository;

    @Autowired
    public SockServiceImpl(SockRepository sockRepository) {
        this.sockRepository = sockRepository;
    }

    @Override
    public List<Sock> getAll(SockCriteria sockCriteria) {
        Specification<Sock> sockSpecification = Specification.where(sockCriteria.getSockColorSpecification())
                .and(sockCriteria.getCompareCottonPartSpecification());
        return sockRepository.findAll(sockSpecification);
    }

    @Override
    public Sock sockOutcome(InOutComeSockDto incomeSockDto) {
        return sockOutcome(incomeSockDto, SockAccounting.IN);
    }

    @Override
    public Sock sockIncome(InOutComeSockDto incomeSockDto) {
        return sockOutcome(incomeSockDto, SockAccounting.OUT);
    }

    private Sock sockOutcome(InOutComeSockDto incomeSockDto, SockAccounting accounting) {
        Sock sock = sockRepository.findByColorAndAndCottonPart(incomeSockDto.getSockColor(), incomeSockDto.getCottonPart())
                .orElse(null);
        if (sock != null) {
            int newSockCount = accounting == SockAccounting.IN ?
                    sock.getCounter() + incomeSockDto.getQuantity() : sock.getCounter() - incomeSockDto.getQuantity();
            if (accounting == SockAccounting.OUT && newSockCount < 0) {
                throw new BadRequestException("new sock count can't be less then 0");
            }
            sock.setCounter(newSockCount);
        } else {
            sock = new Sock(incomeSockDto.getSockColor(), incomeSockDto.getCottonPart());
        }
        return sockRepository.save(sock);
    }

    private enum SockAccounting {
        IN,
        OUT;
    }
}
