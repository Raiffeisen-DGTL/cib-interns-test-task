package com.piminovdmitry.raif_cib_interns_test_task.repository;

import com.piminovdmitry.raif_cib_interns_test_task.entity.StockUnit;
import com.piminovdmitry.raif_cib_interns_test_task.service.CompOperation;

import java.util.List;

public interface StockUnitRepository {
    List<StockUnit> getStockUnitsByParams(String color, int cottonPart, CompOperation compOperation);

    StockUnit saveOrUpdateStockUnit(StockUnit stockUnit);

}
