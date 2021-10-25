package ru.backend.shop.service;

import ru.backend.shop.entities.Color;

public interface ColorService {

    /**
     * Добавляем цвет
     * @param colorName цвет носков
     * @return класс с цветом носков
     */
    Color add(String colorName);

    /**
     * Поиск класс цвета носков
     * @param colorName цвет носков
     * @return класс цвета
     */
    Color findColor(String colorName);
}