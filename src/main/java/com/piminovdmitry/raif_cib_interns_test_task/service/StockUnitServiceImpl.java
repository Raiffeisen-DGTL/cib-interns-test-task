package com.piminovdmitry.raif_cib_interns_test_task.service;

import com.piminovdmitry.raif_cib_interns_test_task.repository.StockUnitRepository;
import com.piminovdmitry.raif_cib_interns_test_task.entity.StockUnit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class StockUnitServiceImpl implements StockUnitService {
    @Autowired
    private StockUnitRepository stockUnitRepository;

    @Override
    @Transactional
    public StockUnit saveStockUnitMove(StockUnit stockUnit, TransactionType transactionType) {
        List<StockUnit> stockUnits = getStockUnitsByParams(stockUnit.getColor(), stockUnit.getCottonPart(), CompOperation.EQUAL);
        StockUnit storedUnitStock = null;
        if (!stockUnits.isEmpty()) {
            storedUnitStock = stockUnits.get(0);
        }
        if (transactionType == TransactionType.INCOME) {
            if (storedUnitStock == null) {
                return stockUnitRepository.saveOrUpdateStockUnit(stockUnit);
            } else {
                storedUnitStock.setQuantity(storedUnitStock.getQuantity() + stockUnit.getQuantity());
                return stockUnitRepository.saveOrUpdateStockUnit(storedUnitStock);
            }
        } else {
            if (storedUnitStock != null && storedUnitStock.getQuantity() >= stockUnit.getQuantity()) {
                storedUnitStock.setQuantity(storedUnitStock.getQuantity() - stockUnit.getQuantity());
                return stockUnitRepository.saveOrUpdateStockUnit(storedUnitStock);
            } else {
                return null;
            }
        }
    }

    @Override
    @Transactional
    public List<StockUnit> getStockUnitsByParams(String color, int cottonPart, CompOperation compOperation) {
        return stockUnitRepository.getStockUnitsByParams(color, cottonPart, compOperation);
    }

    @Override
    public int getQuantitySocksUnitByParams(String color, int cottonPart, CompOperation compOperation) {
        List<StockUnit> list = getStockUnitsByParams(color, cottonPart, compOperation);
        return list.stream().mapToInt(StockUnit::getQuantity).sum();
    }
}
