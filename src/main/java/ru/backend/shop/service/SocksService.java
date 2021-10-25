package ru.backend.shop.service;

import ru.backend.shop.entities.dto.SocksDto;
import java.util.List;

public interface SocksService {

    /**
     * Приход носков на склад
     * @param socks пришедшие носки
     */
    void addSocks(SocksDto socks);

    /**
     * Отпуск носков со склада
     * @param socks отпускаемые носки
     */
    void deleteSocks(SocksDto socks);

    /**
     * Возвращает общее количество носков на складе по параметрам
     * @param color - цвет носков
     * @param operation - оператор сравнения значения количества хлопка в составе носков
     * @param cottonPart - значение процента хлопка в составе носков
     * @return
     */
    List<SocksDto> getSocksByParameters(String color, String operation, Integer cottonPart);
}