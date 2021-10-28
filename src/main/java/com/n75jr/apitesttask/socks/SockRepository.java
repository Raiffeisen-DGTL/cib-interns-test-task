package com.n75jr.apitesttask.socks;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SockRepository extends JpaRepository<Sock, Long> {
    @Query(value = "SELECT id FROM socks WHERE color = :color AND cotton_part = :cotton_part", nativeQuery = true)
    List<Long> outcomeWithoutId(@Param("color") String color, @Param("cotton_part") int cotton_part);

    @Query(value = "SELECT count(*) FROM socks WHERE color = :color AND cotton_part > :cotton_part", nativeQuery = true)
    int operMoreThan(@Param("color") String color, @Param("cotton_part") int cotton_part);

    @Query(value = "SELECT count(*) FROM socks WHERE color = :color AND cotton_part < :cotton_part", nativeQuery = true)
    int operLessThan(@Param("color") String color, @Param("cotton_part") int cotton_part);

    @Query(value = "SELECT count(*) FROM socks WHERE color = :color AND cotton_part = :cotton_part", nativeQuery = true)
    int operEqual(@Param("color") String color, @Param("cotton_part") int cotton_part);
}
