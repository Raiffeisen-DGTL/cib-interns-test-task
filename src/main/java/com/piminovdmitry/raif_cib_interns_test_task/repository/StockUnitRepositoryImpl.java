package com.piminovdmitry.raif_cib_interns_test_task.repository;

import com.piminovdmitry.raif_cib_interns_test_task.entity.StockUnit;

import com.piminovdmitry.raif_cib_interns_test_task.service.CompOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

@Repository
public class StockUnitRepositoryImpl implements StockUnitRepository {

    @Autowired
    private EntityManager entityManager;

    @Override
    public List<StockUnit> getStockUnitsByParams(String color, int cottonPart, CompOperation compOperation) {
        Query query = entityManager.createQuery("from StockUnit " +
                "where color =:color and cotton_part " + compOperation.getMathSimbol() + ":cottonPart");
        query.setParameter("color", color);
        query.setParameter("cottonPart", cottonPart);
        return query.getResultList();
    }

    @Override
    public StockUnit saveOrUpdateStockUnit(StockUnit stockUnit) {
        return entityManager.merge(stockUnit);
    }
}
