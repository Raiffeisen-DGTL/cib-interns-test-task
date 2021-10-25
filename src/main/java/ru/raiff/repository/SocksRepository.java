package ru.raiff.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.raiff.entity.Socks;

import java.util.Optional;

public interface SocksRepository extends JpaRepository<Socks, Integer> {

    @Query("SELECT socks.id FROM Socks AS socks WHERE socks.color = :color AND socks.cottonPart = :cottonPart")
    Optional<Integer> getSocksLessThan(String color, int cottonPart);

    @Query("SELECT SUM (socks.quantity) FROM Socks AS socks WHERE socks.color = :color AND socks.cottonPart = :cottonPart")
    Optional<Integer> getSocksEqual(String color, int cottonPart);

    @Query("SELECT SUM (socks.quantity) FROM Socks AS socks WHERE socks.color = :color AND socks.cottonPart > :cottonPart")
    Optional<Integer> getSocksMoreThan(String color, int cottonPart);

    @Query("SELECT SUM (socks.quantity) FROM Socks AS socks WHERE socks.color = :color AND socks.cottonPart < :cottonPart")
    Optional<Integer> getIdByColorAndCottonPart(String color, int cottonPart);
}


