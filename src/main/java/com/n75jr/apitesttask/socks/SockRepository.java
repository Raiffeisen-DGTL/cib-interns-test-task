package com.n75jr.apitesttask.socks;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;

public interface SockRepository extends JpaRepository<Sock, Long> {
    @Modifying
    @Transactional
    @Query(value = "INSERT INTO socks AS s (color, cotton_part, quantity) " +
            "VALUES (:color, :cotton_part, :quantity) " +
            "ON CONFLICT ON CONSTRAINT socks_pkey " +
            "DO UPDATE SET quantity = s.quantity + :quantity", nativeQuery = true)
    int income(@Param("color") String col, @Param("cotton_part") int cottonPart, @Param("quantity") int quantity);

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO socks AS s (color, cotton_part, quantity) " +
            "VALUES (:color, :cotton_part, :quantity) " +
            "ON CONFLICT ON CONSTRAINT socks_pkey " +
            "DO UPDATE SET quantity = s.quantity - :quantity", nativeQuery = true)
    int outcome(@Param("color") String col, @Param("cotton_part") int cottonPart, @Param("quantity") int quantity);

    @Query(value = "SELECT sum(quantity) FROM socks WHERE color = :color AND cotton_part > :cotton_part", nativeQuery = true)
    Long operMoreThan(@Param("color") String color, @Param("cotton_part") int cotton_part) throws NullPointerException;

    @Query(value = "SELECT sum(quantity) FROM socks WHERE color = :color AND cotton_part < :cotton_part", nativeQuery = true)
    Long operLessThan(@Param("color") String color, @Param("cotton_part") int cotton_part) throws NullPointerException;

    @Query(value = "SELECT sum(quantity) FROM socks WHERE color = :color AND cotton_part = :cotton_part", nativeQuery = true)
    Long operEqual(@Param("color") String color, @Param("cotton_part") int cotton_part) throws NullPointerException;
}
