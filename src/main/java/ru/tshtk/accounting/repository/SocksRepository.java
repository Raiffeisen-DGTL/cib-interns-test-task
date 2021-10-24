package ru.tshtk.accounting.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.tshtk.accounting.entity.Socks;

import java.util.Optional;

public interface SocksRepository extends JpaRepository <Socks,Integer> {

    @Query("SELECT socks.id FROM Socks AS socks WHERE socks.color = :color AND socks.cottonPart = :cottonPart")
    Optional<Integer> getIdByColorAndCottonPart(@Param("color") String color, @Param("cottonPart") int cottonPart);

    @Query("SELECT SUM (socks.quantity) FROM Socks AS socks WHERE socks.color = :color AND socks.cottonPart = :cottonPart")
    Optional<Integer> getSocksEqual(@Param("color") String color, @Param("cottonPart") int cottonPart);

    @Query("SELECT SUM (socks.quantity) FROM Socks AS socks WHERE socks.color = :color AND socks.cottonPart < :cottonPart")
    Optional<Integer> getSocksLessThan(@Param("color") String color, @Param("cottonPart") int cottonPart);

    @Query("SELECT SUM (socks.quantity) FROM Socks AS socks WHERE socks.color = :color AND socks.cottonPart > :cottonPart")
    Optional<Integer> getSocksMoreThan(@Param("color") String color, @Param("cottonPart") int cottonPart);
}