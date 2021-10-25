package com.piminovdmitry.raif_cib_interns_test_task.service;

import com.piminovdmitry.raif_cib_interns_test_task.entity.StockUnit;

import java.util.List;

public interface StockUnitService {
    StockUnit saveStockUnitMove(StockUnit stockUnit, TransactionType transactionType);

    List<StockUnit> getStockUnitsByParams(String color, int cottonPart, CompOperation compOperation);

    int getQuantitySocksUnitByParams(String color, int cottonPart, CompOperation compOperation);
}
