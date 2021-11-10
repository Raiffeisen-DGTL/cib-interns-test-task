package com.example.socksstorage.service;

import com.example.socksstorage.model.Socks;

import java.util.List;

public interface StorageService {
    /**
     * Создает новую партию носков
     * @param color - цвет
     * @param cottonPart - содержание хлопка
     * @param quantity - количество
     * */
    boolean create(String color, int cottonPart, int quantity);

    /**
     * Возвращает список носков
     * @param color - цвет
     * @param cottonPart - содержание хлопка
     * @param operation - сравнение
     * @return socks list*/
    int readAll(String color,
                        Socks.Operation operation,
                        int cottonPart);

    /**
     * Удаляет носки с заданным ID
     * @param id - id носков, которых нужно удалить
     * @return - true если носки были удалены, иначе false
     */
    boolean deleteById(int id);

    /**
     * Удалает носки по заданным параметрам
     *
     * @param color - цвет носков
     * @param cottonPart - количество хлопка в носке*/
    boolean delete(String color, int cottonPart, int quantity);
}


