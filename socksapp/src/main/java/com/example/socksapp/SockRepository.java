package com.example.socksapp;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

/**
 * Репозиторий для взаимодействия с таблицей носков
 */
public interface SockRepository extends JpaRepository<Socks, Long> {

    /**
     * Получить носки по цвету и процентному содержанию хлопка в составе
     */
    Optional<Socks> findByColorAndCottonPart(String color, Integer cottonPart);


    /**
     * Получить список носков с хлопком меньшим указанного параметра
     */
    Optional<List<Socks>> findByColorAndCottonPartLessThan(String color,Integer cottonPart);

    /**
     * Получить список носков с хлопком больше указанного параметра
     */
    Optional<List<Socks>> findByColorAndCottonPartGreaterThan(String color,Integer cottonPart);

}
