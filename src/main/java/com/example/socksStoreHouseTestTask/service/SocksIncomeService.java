package com.example.socksStoreHouseTestTask.service;

import com.example.socksStoreHouseTestTask.entity.SocksEntity;
import com.example.socksStoreHouseTestTask.repo.SocksRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SocksIncomeService {

    @Autowired
    private SocksRepo socksRepo;
    private SocksEntity findSocks;

    public SocksEntity income(SocksEntity socks) {
        if (socks.getColor() == null || socks.getCottonPart() < 0 || socks.getCottonPart() > 100 || socks.getQuantity() <= 0) {
            throw new IllegalArgumentException();
        }
        findSocks = socksRepo.findAllByColorAndCottonPart(socks.getColor(), socks.getCottonPart());
        if (findSocks != null) {
            findSocks.setQuantity(socks.getQuantity() + findSocks.getQuantity());
            return socksRepo.save(findSocks);
        } else {
            return socksRepo.save(socks);
        }
    }
}
