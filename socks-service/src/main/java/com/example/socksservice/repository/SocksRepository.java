package com.example.socksservice.repository;

import com.example.socksservice.entity.Sock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface SocksRepository extends JpaRepository<Sock, Long> {

    Optional<Sock> findByColorAndCottonPart(String color, int cottonPart);

    @Query("SELECT SUM (s.quantity) from Sock s WHERE s.color = ?1 AND s.cottonPart > ?2")
    Long countByColorAndAndCottonGtTh(String color, int cotton);

    @Query("SELECT SUM (s.quantity) from Sock s WHERE s.color = ?1 AND s.cottonPart < ?2")
    Long countByColorAndAndCottonLsTh(String color, int cotton);

    @Query("SELECT SUM (s.quantity) from Sock s WHERE s.color = ?1 AND s.cottonPart = ?2")
    Long countByColorAndAndCottonEq(String color, int cotton);
}
