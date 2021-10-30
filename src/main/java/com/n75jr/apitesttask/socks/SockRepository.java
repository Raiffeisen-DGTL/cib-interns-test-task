package com.n75jr.apitesttask.socks;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.List;

public interface SockRepository extends JpaRepository<Sock, Long> {
    @Modifying
    @Transactional
    @Query(value = "UPDATE socks SET quantity = quantity + :quantity WHERE color = :color AND cotton_part = :cotton_part", nativeQuery = true)
    int income(@Param("color") String col, @Param("cotton_part") int cottonPart, @Param("quantity") int quantity);

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM socks WHERE color = :color AND cotton_part = :cotton_part AND quantity = :quantity", nativeQuery = true)
    int outcome(@Param("color") String col, @Param("cotton_part") int cottonPart, @Param("quantity") int quantity);

    @Query(value = "SELECT quantity FROM socks WHERE color = :color AND cotton_part > :cotton_part", nativeQuery = true)
    int operMoreThan(@Param("color") String color, @Param("cotton_part") int cotton_part);

    @Query(value = "SELECT quantity FROM socks WHERE color = :color AND cotton_part < :cotton_part", nativeQuery = true)
    int operLessThan(@Param("color") String color, @Param("cotton_part") int cotton_part);

    @Query(value = "SELECT quantity FROM socks WHERE color = :color AND cotton_part = :cotton_part", nativeQuery = true)
    int operEqual(@Param("color") String color, @Param("cotton_part") int cotton_part);
}
