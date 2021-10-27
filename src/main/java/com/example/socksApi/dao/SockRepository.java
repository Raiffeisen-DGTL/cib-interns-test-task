package com.example.socksApi.dao;

import com.example.socksApi.model.Sock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SockRepository extends JpaRepository<Sock, Long> {

    Optional<Sock> findSockByColorAndCottonPart(String color, int cottonPart);

    @Query("SELECT SUM(s.amount) FROM Sock s WHERE s.cottonPart=:cotton AND s.color=:color")
    Optional<Integer> findCountEqual(@Param("cotton") int cotton, @Param("color") String color);

    @Query("SELECT SUM(s.amount) FROM Sock s WHERE s.cottonPart>:cotton AND s.color=:color")
    Optional<Integer> findCountMoreThan(@Param("cotton") int cotton, @Param("color") String color);

    @Query("SELECT SUM(s.amount) FROM Sock s WHERE s.cottonPart<:cotton AND s.color=:color")
    Optional<Integer> findCountLessThan(@Param("cotton") int cotton, @Param("color") String color);

}
