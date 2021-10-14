package com.example.socksspring.service;

import com.example.socksspring.Compare;
import com.example.socksspring.Socks;
import com.example.socksspring.controller.SocksController;
import com.example.socksspring.repositories.SocksRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    public List<Socks> getSocks(String color, Compare op, int cottonPart) {
        switch (op) {
            case equals:
                return socksRepository.getSocksEquals(color, cottonPart);
            case lessThan:
                return socksRepository.getSocksLessThan(color, cottonPart);
            case moreThan:
                return socksRepository.getSocksGreaterThan(color, cottonPart);
        }
        return null;
    }

    @Transactional
    public void addSocks(Socks socks) {
        List<Socks> socksList = socksRepository.upsertAddSocks(socks);
        logger.debug("adding socks in socks service: " + socksList.get(0));
    }

    @Transactional
    public List<Socks> removeSocks(Socks socks) {
        return socksRepository.updateRemoveSocks(socks);
    }
}
