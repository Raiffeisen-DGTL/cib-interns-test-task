package com.example.demo.service;

import com.example.demo.entity.Socks;
import com.example.demo.repo.SocksRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.*;


@Service
@Transactional
public class SocksService {
    private SocksRepo repo;

    public SocksService(SocksRepo repo) {
        this.repo = repo;
    }

    public void postIncome(String color, int cottonPart, int quantity) {
        repo.findQuantityByColorAndCottonPart(color, cottonPart).ifPresentOrElse(
                (value) -> repo.updateQuantityByColorAndCottonPart
                        (quantity + value, color, cottonPart),
                () -> repo.saveAndFlush(new Socks(color, cottonPart, quantity)));
    }

    public void postOutcome(String color, int cottonPart, int quantity) throws NoSuchElementException {
        int foundQuantity = repo.findQuantityByColorAndCottonPart(color, cottonPart).orElseThrow();
        if (foundQuantity - quantity <= 0) {
            repo.deleteByColorAndCottonPart(color, cottonPart);
        } else {
            repo.updateQuantityByColorAndCottonPart(foundQuantity - quantity, color, cottonPart);
        }
    }

    public int getAmountOfSocks(String color, String operation, int cottonPart) throws NoSuchElementException {
        switch (operation) {
            case ("moreThan"):
                return repo.sumByColorAndCottonPartGreaterThan(color, cottonPart).orElseThrow();
            case ("lessThan"):
                return repo.sumByColorAndCottonPartLessThan(color, cottonPart).orElseThrow();
            case ("equal"):
                return repo.sumByColorAndCottonPartEquals(color, cottonPart).orElseThrow();
            default:
                return 0;
        }
    }
}