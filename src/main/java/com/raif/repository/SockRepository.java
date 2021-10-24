package com.raif.repository;

import com.raif.model.Sock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.ArrayList;

public interface SockRepository extends JpaRepository<Sock, Integer> {

    @Query("SELECT s FROM Sock s WHERE (s.color) LIKE :color AND (s.cottonPart) = :cottonPart")
    public ArrayList<Sock> findAllByColorAndCottonPart(@Param("color") String color, @Param("cottonPart") Integer cottonPart);

    @Query("SELECT s FROM Sock s WHERE (s.color) LIKE :color AND (s.cottonPart) > :cottonPart")
    public ArrayList<Sock> findAllByColorAndMoreThanCottonPart(@Param("color") String color, @Param("cottonPart") Integer cottonPart);

    @Query("SELECT s FROM Sock s WHERE (s.color) LIKE :color AND (s.cottonPart) < :cottonPart")
    public ArrayList<Sock> findAllByColorAndLessThanCottonPart(@Param("color") String color, @Param("cottonPart") Integer cottonPart);

}
