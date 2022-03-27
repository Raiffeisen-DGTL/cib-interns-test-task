package com.zpsx.cibinternstesttask.service;

import com.zpsx.cibinternstesttask.domain.Operation;
import com.zpsx.cibinternstesttask.domain.SockStock;
import com.zpsx.cibinternstesttask.repo.SockStockRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@Service
public class SocksService {

    @Autowired SockStockRepository sockStockRepo;

    public Long getSocksQuantity(String color, Operation operation, Byte cottonPart){
        color = color.toLowerCase(Locale.ROOT);
        List<SockStock> sockStocks = new ArrayList<>();

        switch (operation){
            case equal: sockStocks = sockStockRepo.findAllByColorAndCottonPart(color, cottonPart); break;
            case moreThan: sockStocks = sockStockRepo.findAllByColorAndCottonPartGreaterThan(color, cottonPart); break;
            case lessThan: sockStocks = sockStockRepo.findAllByColorAndCottonPartLessThan(color, cottonPart); break;
        }

        Long quantity = 0L;
        for(SockStock sockStock: sockStocks){
            quantity += sockStock.getQuantity();
        }

        return quantity;
    }

    public void addSocks(SockStock income){
        SockStock sockStock = sockStockRepo.findByColorAndCottonPart(
                income.getColor().toLowerCase(Locale.ROOT),
                income.getCottonPart());

        if(sockStock==null) {
            income.setColor(income.getColor().toLowerCase(Locale.ROOT));
            sockStock = income;
        }
        else
            sockStock.setQuantity(sockStock.getQuantity() + income.getQuantity());

        sockStockRepo.save(sockStock);
    }

    public void withdrawSocks(SockStock withdraw){
        SockStock sockStock = sockStockRepo.findByColorAndCottonPart(
                withdraw.getColor().toLowerCase(Locale.ROOT),
                withdraw.getCottonPart());
        if(!(sockStock != null && sockStock.getQuantity() - withdraw.getQuantity() >= 0))
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);

        sockStock.setQuantity(sockStock.getQuantity() - withdraw.getQuantity());
        sockStockRepo.save(sockStock);
    }
}
