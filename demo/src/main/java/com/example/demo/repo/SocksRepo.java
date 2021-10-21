package com.example.demo.repo;

import com.example.demo.entity.Socks;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SocksRepo extends JpaRepository<Socks, Integer> {

    @Query(value = "SELECT quantity FROM socks WHERE color=?1 AND cotton_part=?2", nativeQuery = true)
    Optional<Integer> findQuantityByColorAndCottonPart(String color, int cottonPart);

    @Query(value = "SELECT SUM(quantity) FROM socks WHERE color=?1 AND cotton_part>?2", nativeQuery = true)
    Optional<Integer> sumByColorAndCottonPartGreaterThan(String color, int cottonPart);

    @Query(value = "SELECT SUM(quantity) FROM socks WHERE color=?1 AND cotton_part<?2", nativeQuery = true)
    Optional<Integer> sumByColorAndCottonPartLessThan(String color, int cottonPart);

    @Query(value = "SELECT SUM(quantity) FROM socks WHERE color=?1 AND cotton_part=?2", nativeQuery = true)
    Optional<Integer> sumByColorAndCottonPartEquals(String color, int cottonPart);

    @Modifying
    @Query(value = "DELETE  FROM socks WHERE color=?1 AND cotton_part=?2", nativeQuery = true)
    void deleteByColorAndCottonPart(String color, int cottonPart);

    @Modifying
    @Query(value = "UPDATE socks AS s SET quantity=?1 WHERE s.color=?2 AND s.cotton_part=?3", nativeQuery = true)
    void updateQuantityByColorAndCottonPart(int quantity, String color, int cottonPart);
}


