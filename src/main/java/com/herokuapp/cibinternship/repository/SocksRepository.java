package com.herokuapp.cibinternship.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.herokuapp.cibinternship.model.Socks;
import com.herokuapp.cibinternship.model.SocksId;

import java.util.Optional;

@Repository
public interface SocksRepository extends JpaRepository<Socks, SocksId> {
    Optional<Socks> findById(SocksId socksId);

    @Query("SELECT SUM(quantity) FROM Socks WHERE color = ?1 AND cottonPart < ?2")
    Optional<Integer> sumQuantityByColorAndCottonPartLessThan(String color, int cottonPart);

    @Query("SELECT SUM(quantity) FROM Socks WHERE color = ?1 AND cottonPart > ?2")
    Optional<Integer> sumQuantityByColorAndCottonPartGreaterThan(String color, int cottonPart);

    @Query("SELECT SUM(quantity) FROM Socks WHERE color = ?1 AND cottonPart = ?2")
    Optional<Integer> sumQuantityByColorAndCottonPartEquals(String color, int cottonPart);
}
