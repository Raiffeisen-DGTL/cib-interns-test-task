package com.example.socksstr.Repository;

import com.example.socksstr.Model.BaseEntity;
import com.example.socksstr.Model.Socks;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface SocksRepository extends JpaRepository<Socks, BaseEntity> {

    Optional<Socks> findById(BaseEntity baseEntity);

    @Query("SELECT SUM(quantity) FROM Socks WHERE color = ?1 AND cottonPart < ?2")
    Optional<Integer> sumQuantityByColorAndCottonPartLessThan(String color, long cottonPart);

    @Query("SELECT SUM(quantity) FROM Socks WHERE color = ?1 AND cottonPart > ?2")
    Optional<Integer> sumQuantityByColorAndCottonPartGreaterThan(String color, long cottonPart);

    @Query("SELECT SUM(quantity) FROM Socks WHERE color = ?1 AND cottonPart = ?2")
    Optional<Integer> sumQuantityByColorAndCottonPartEquals(String color, long cottonPart);

}
