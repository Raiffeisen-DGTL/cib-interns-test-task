package com.test.socksapp.repository;

import com.test.socksapp.entity.Sock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface SockRepository extends JpaRepository<Sock, Long> {

    @Query("SELECT s FROM Sock s WHERE s.color = ?1 AND s.cottonpart < ?2")
    List<Sock> findWhereLess(String color, int cottonPart);

    @Query("SELECT s FROM Sock s WHERE s.color = ?1 AND s.cottonpart > ?2")
    List<Sock> findWhereMore(String color, int cottonPart);

    @Query("SELECT s FROM Sock s WHERE s.color = ?1 AND s.cottonpart = ?2")
    List<Sock> findWhereEqual(String color, int cottonPart);

    @Query("UPDATE Sock s SET s.quantity = ?3 WHERE  s.color = ?1 AND s.cottonpart = ?2")
    void updateBy(String color, int cottonPart, int quantity);

    @Query("SELECT s FROM Sock s WHERE s.color = ?1 AND s.cottonpart = ?2")
    Sock findByColorAndCottonpart(String color, int cottonPart);

}
