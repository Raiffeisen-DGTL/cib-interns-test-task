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
        if (cotton < 0 || cotton > 100) {
            throw new RuntimeException("Неверно задано значение cotton.Значение должно быть " +
                    "целым числом в диапазоне от 0 до 100");
        }

        String colorToUpperCase = color.toUpperCase();
        String operationToUpperCase = operation.toUpperCase();
        int result = switch (operationToUpperCase) {
            case "MORETHAN" -> socksRepo.findSocksMoreThan(colorToUpperCase, cotton);
            case "EQUAL" -> socksRepo.findSocksEqual(colorToUpperCase, cotton);
            case "LESSTHAN" -> socksRepo.findSocksLessThan(colorToUpperCase, cotton);
            default -> throw new RuntimeException("Не верно задан operation " + operation);
        };

        return result;
    }
}
