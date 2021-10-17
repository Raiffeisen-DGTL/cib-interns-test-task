package com.example.bootcamp.service;

import com.example.bootcamp.dto.SocksDto;

import java.util.List;

public interface OutcomeService {

    /**
     *
     * @param socksDtos список носков разного цвета и содержания хлопка
     *
     * Уменьшает на заданное количество носков значение в базе данных,сгруппированное по цвету и содержанию хлопка,
     * если количества хватает,удаляет наименование в базе данных,если заданное количество =
     *                  количеству в базе данных,или выкидывает исключение,если количества недостаточно
     */

    void outcome(List<SocksDto> socksDtos);
}
