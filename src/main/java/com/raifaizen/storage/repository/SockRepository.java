package com.raifaizen.storage.repository;

import com.raifaizen.storage.models.Sock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface SockRepository extends JpaRepository<Sock, Long> {
    List<Sock> findByColor(String color);

    @Query("from Sock p where p.color = :color AND p.cottonPart = :cottonPart")
    List<Sock> findByColorAndCottonPartEqual(String color, int cottonPart);

    @Query("from Sock p where p.color = :color AND p.cottonPart < :cottonPart")
    List<Sock> findByColorAndCottonPartLess(String color, int cottonPart);

    @Query("from Sock p where p.color = :color AND p.cottonPart > :cottonPart")
    List<Sock> findByColorAndCottonPartMore(String color, int cottonPart);

    @Query("from Sock p where p.cottonPart = :cottonPart")
    List<Sock> findByCottonPartEqual(int cottonPart);

    @Query("from Sock p where p.cottonPart < :cottonPart")
    List<Sock> findByCottonPartLess(int cottonPart);

    @Query("from Sock p where p.cottonPart > :cottonPart")
    List<Sock> findByCottonPartMore(int cottonPart);
}
