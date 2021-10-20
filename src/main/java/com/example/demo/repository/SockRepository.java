package com.example.demo.repository;

import com.example.demo.model.Sock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface SockRepository extends JpaRepository<Sock, Long> {

    Sock findById(long id);

    List<Sock> findByColor(String color);

    @Query("SELECT socs FROM Sock socs WHERE socs.cottonPart = :cottonPart and socs.color = :color")
    List<Sock> findByColorAndCottonPart(
            @Param("color") String color,
            @Param("cottonPart") Integer cottonPart);

    @Query("SELECT SUM(socs.quantity) FROM Sock socs WHERE socs.cottonPart = :cottonPart and socs.color = :color")
    int sumQuantityWhenColorEqualsAndCottonPartEquals(
            @Param("color") String color,
            @Param("cottonPart") Integer cottonPart);

    @Query("SELECT SUM(socs.quantity) FROM Sock socs WHERE socs.cottonPart > :cottonPart and socs.color = :color")
    int sumQuantityWhenColorEqualsAndCottonPartMoreThan(
            @Param("color") String color,
            @Param("cottonPart") Integer cottonPart);

    @Query("SELECT SUM(socs.quantity) FROM Sock socs WHERE socs.cottonPart < :cottonPart and socs.color = :color")
    int sumQuantityWhenColorEqualsAndCottonPartLessThan(
            @Param("color") String color,
            @Param("cottonPart") Integer cottonPart);

}