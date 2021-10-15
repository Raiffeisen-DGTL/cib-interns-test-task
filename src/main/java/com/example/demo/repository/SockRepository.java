package com.example.demo.repository;

import com.example.demo.domain.Sock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface SockRepository extends JpaRepository<Sock, Integer> {
    // Запрос для нахождения нужных носков
    @Query("select s from Sock s where s.color = ?1 and s.cottonPart = ?2")
    Optional<Sock> findByColorAndCottonPart(String color, Integer cottonPart);

    // Запросы для подсчета количества носков по условию
    @Query("select coalesce(sum(s.quantity), 0) from Sock s where s.color = ?1 and s.cottonPart > ?2")
    Long getSockCountMoreThanCottonValue(String color, Integer cottonPart);

    @Query("select coalesce(sum(s.quantity), 0) from Sock s where s.color = ?1 and s.cottonPart < ?2")
    Long getSockCountLessThanCottonValue(String color, Integer cottonPart);

    @Query("select coalesce(sum(s.quantity), 0) from Sock s where s.color = ?1 and s.cottonPart = ?2")
    Long getSockCountEqualCottonValue(String color, Integer cottonPart);

}
