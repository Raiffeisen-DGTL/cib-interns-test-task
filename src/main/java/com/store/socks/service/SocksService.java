package com.store.socks.service;

import com.store.socks.mapper.SocksMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SocksService {
    @Autowired
    private final SocksMapper socksMapper;

    public SocksService(SocksMapper socksMapper) {
        this.socksMapper = socksMapper;
    }

    public Optional<Integer> getSocksNumberGivenParams(String color, Operation operation, int cottonPart) {
        Optional<Integer> quantity;
        switch (operation) {
            case MORE_THAN:
                quantity = socksMapper.selectQuantityByParamsMoreThan(color, cottonPart);
                break;
            case LESS_THAN:
                quantity = socksMapper.selectQuantityByParamsLessThan(color, cottonPart);
                break;
            case EQUAL:
                quantity = socksMapper.selectQuantityByParamsEqual(color, cottonPart);
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + operation);
        }
        return quantity;
    }

    public boolean registerSocksIncome(String color, int cottonPart, int quantity) {
        Optional<Integer> socksQuantity = socksMapper.selectQuantityByParamsEqual(color, cottonPart);
        if (socksQuantity.isPresent()) {
            int dbQuantity = socksQuantity.get();
            return socksMapper.updateSocksQuantityGivenParams(color, cottonPart, dbQuantity + quantity);
        } else {
            socksMapper.insertSocksEntry(color, cottonPart, quantity);
            return true;
        }
    }

    public boolean registerSocksOutcome(String color, int cottonPart, int quantity) {
        Optional<Integer> socksQuantity = socksMapper.selectQuantityByParamsEqual(color, cottonPart);
        if (socksQuantity.isPresent()) {
            int dbQuantity = socksQuantity.get();
            if (quantity > dbQuantity) {
                return false;
            } else {
                return socksMapper.updateSocksQuantityGivenParams(color, cottonPart, dbQuantity - quantity);
            }
        } else {
            return false;
        }
    }
}
