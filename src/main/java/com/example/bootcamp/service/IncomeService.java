package com.example.bootcamp.service;

import com.example.bootcamp.dto.SocksDto;

import java.util.List;

public interface IncomeService {
    /**
     *
     * @param socksDtos список носков разного цвета и содержания хлопка
     *
     * Увеличивает на заданное количество носков значение в базе данных,сгруппированное по цвету и содержанию хлопка,
     * если оно уже имеется там или устанавливает новую строку с данными параметрами в базе данных
     *
     */
    void income(List<SocksDto> socksDtos);

}
