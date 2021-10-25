package ru.raiffeisen.socks.service;

import com.sun.istack.NotNull;
import ru.raiffeisen.socks.service.impl.Operation;

/**
 * Сервис для работы с носками.
 */
public interface SockService {

    /**
     * Возвращает общее количество носков на складе, соответствующих переданным в параметрах критериям запроса.
     */
    int getQuantityOfSocks(@NotNull String color, @NotNull Operation operation, int cottonPart);

    /**
     * Регистрирует приход носков на склад.
     */
    void registerArrivalOfSocks(@NotNull String color, int cottonPart, int quantity);

    /**
     * Регистрирует отпуск носков со склада.
     */
    void registerReleaseOfSocks(@NotNull String color, int cottonPart, int quantity);
}
