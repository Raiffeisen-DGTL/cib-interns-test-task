package com.example.bootcamp.service;

import com.example.bootcamp.dto.Operation;
import com.example.bootcamp.entity.SocksEntity;

import java.util.List;

public interface GetSocksService {

    /**
     *
     * @param color необходимый цвет пар носков
     * @param operation оператор сравнения значения количества хлопка в составе носков,
     *                  одно значение из: moreThan, lessThan, equal
     * @param cotton значение процента хлопка в составе носков из сравнения
     * @return общее количество носков на складе, соответствующих переданным в параметрах критериям запроса
     */
    int getSocks(String color, Operation operation, short cotton);
}
