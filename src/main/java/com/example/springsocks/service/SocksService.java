package com.example.springsocks.service;

import com.example.springsocks.domain.Socks;

/**
 * Сервис для работы с носками.
 *
 * @author Alexander_Tupchin
 */
public interface SocksService {

    /**
     * Возвращает общее количество носков на складе, соответствующих переданным в параметрах критериям запроса.
     *
     * @param color цвет носков
     * @param operation оператор сравнения значения количества хлопка в составе носков
     * @param cottonPart процентное содержание хлопка в составе носков
     * @return количество носков
     */
    Long getCountSocks(String color, String operation, Integer cottonPart);

    /**
     * Добавляет на склад носки.
     *
     * @param socks носки
     */
    void addSocks(Socks socks);

    /**
     * Изымает носки со склада.
     *
     * @param socks носки
     */
    void reduceSocks(Socks socks);

}
