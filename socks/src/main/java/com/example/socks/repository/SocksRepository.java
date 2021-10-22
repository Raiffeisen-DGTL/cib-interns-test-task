package com.example.socks.repository;

import com.example.socks.model.Socks;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SocksRepository extends JpaRepository<Socks, Long> {

    @Query("SELECT SUM(s.quantity) FROM Socks AS s WHERE s.color = :color AND s.cottonPart > :cottonPart")
    Optional<Integer> getSocksMoreThan(@Param("color") String color, @Param("cottonPart") int cottonPart);

    @Query("SELECT SUM (s.quantity) FROM Socks AS s WHERE s.color = :color AND s.cottonPart < :cottonPart")
    Optional<Integer> getSocksLessThan(@Param("color") String color, @Param("cottonPart") int cottonPart);

    @Query("SELECT SUM (s.quantity) FROM Socks AS s WHERE s.color = :color AND s.cottonPart = :cottonPart")
    Optional<Integer> getSocksEqual(@Param("color") String color, @Param("cottonPart") int cottonPart);

//    @Query("SELECT s.color, s.cottonPart, s.quantity FROM Socks AS s WHERE s.color = :color AND s.cottonPart = :cottonPart")
//    Socks findSocks(@Param("color") String color, @Param("cottonPart") int cottonPart);

//    @Query("UPDATE Socks AS s SET s.quantity = s.quantity - :quantity WHERE s.color = :color AND s.cottonPart = :cottonPart")
//    void updateSocks(@Param("color") String color, @Param("cottonPart") int cottonPart, @Param("quantity") int quantity);

//    @Query("UPDATE Socks AS s SET s.quantity = :quantity WHERE s.id = :id")
//    void updateSocks(@Param("id") Long id, @Param("quantity") int quantity);

    @Query("SELECT s.id FROM Socks AS s WHERE s.color = :color AND s.cottonPart = :cottonPart")
    Optional<Long> getIdByColorAndCottonPart(@Param("color") String color, @Param("cottonPart") int cottonPart);
}
