package com.example.testsocks.service;

import com.example.testsocks.model.Socks;
import com.example.testsocks.model.SocksPK;
import com.example.testsocks.repository.SocksRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class SocksServiceImpl implements SocksService {

    @Autowired
    private SocksRepository socksRepository;

    @Override
    @Transactional
    public void income(Socks socks) {
        socksRepository.income(socks);
    }

    @Override
    @Transactional
    public boolean outcome(Socks socks) {
        return socksRepository.outcome(socks);
    }

    @Override
    @Transactional
    public int countSocks(SocksPK socksPK, String operation) {
        switch (operation) {
            case "moreThan":
                 return socksRepository.countMoreThan(socksPK);
            case "lessThan":
                 return socksRepository.countLessThan(socksPK);
            case "equals":
                 return socksRepository.getQuantity(socksPK);
            default:
                return 0;
        }
    }
}
