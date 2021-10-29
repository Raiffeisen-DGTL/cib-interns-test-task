package com.example.socksspring.service;

import com.example.socksspring.Compare;
import com.example.socksspring.Socks;
import com.example.socksspring.controller.SocksController;
import com.example.socksspring.repositories.SocksRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class SocksService {
    private final SocksRepository socksRepository;
    private final Logger logger;

    public SocksService(SocksRepository repository) {
        this.socksRepository = repository;
        this.logger = LoggerFactory.getLogger(SocksController.class);
    }

    @Transactional
    public Integer getAmountOfSocks(String color, Compare op, int cottonPart) {
        Integer result = 0;

        List<Socks> queriedSocks = new ArrayList<>();
        switch (op) {
            case Equals:
                queriedSocks.addAll(socksRepository.getSocksEquals(color, cottonPart));
            case LessThan:
                queriedSocks.addAll(socksRepository.getSocksLessThan(color, cottonPart));
            case MoreThan:
                queriedSocks.addAll(socksRepository.getSocksGreaterThan(color, cottonPart));
        }

        for (Socks socks : queriedSocks) {
            result += socks.getQuantity();
        }
        return result;
    }

    @Transactional
    public Boolean addSocks(Socks socks) {
        List<Socks> socksList = socksRepository.upsertAddSocks(socks);
        logger.debug("adding socks in socks service: " + socksList.get(0));
        return true;
    }

    @Transactional
    public List<Socks> removeSocks(Socks socks) {
        return socksRepository.updateRemoveSocks(socks);
    }
}
