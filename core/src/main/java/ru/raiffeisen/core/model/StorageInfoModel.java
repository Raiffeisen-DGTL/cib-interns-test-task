package ru.raiffeisen.core.model;

import lombok.Data;

@Data
public class StorageInfoModel {

    /**
     * Цвет носков.
     */
    private String color;

    /**
     * Процентное содержание хлопка в составе носков.
     */
    private Integer cottonPart;

    /**
     * Количество пар носков.
     */
    private Integer quantity;
}
