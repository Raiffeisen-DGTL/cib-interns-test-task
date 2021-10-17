package com.example.bootcamp.service;

import com.example.bootcamp.repo.SocksRepo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class GetSocksServiceImpl implements GetSocksService {

    private final SocksRepo socksRepo;

    public GetSocksServiceImpl(SocksRepo socksRepo) {
        this.socksRepo = socksRepo;
    }

    @Transactional
    @Override
    public int getSocks(String color, String operation, int cotton) {


        int result = switch (operation) {
            case "moreThan" -> socksRepo.findSocksMoreThan(color, cotton);
            case "equal" -> socksRepo.findSocksEqual(color, cotton);
            case "lessThan" -> socksRepo.findSocksLessThan(color, cotton);
            default -> throw new RuntimeException("Не верно задан operation " + operation);
        };

        return result;
    }
}
