package com.example.socksstorage.service;

import com.example.socksstorage.model.Socks;
import java.util.List;

public interface StorageService {
    /**
     * Создает новую пару носков
     * @param socks
     * */
    void create(Socks socks);

    /**
     * Возвращает список носков
     * @return socks list*/
    List<Socks> readAll();

    /**
     * Удаляет носки с заданным ID
     * @param id - id носков, которых нужно удалить
     * @return - true если носки были удалены, иначе false
     */
    boolean delete(int id);
}


