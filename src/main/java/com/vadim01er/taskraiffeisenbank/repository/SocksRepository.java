package com.vadim01er.taskraiffeisenbank.repository;

import com.vadim01er.taskraiffeisenbank.entity.Socks;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SocksRepository extends JpaRepository<Socks, Long> {
    Socks findByColorAndCottonPart(String color, Short cottonPart);

    @Query(value = "select id, color, cotton_part, quality from Socks s where s.color LIKE :color " +
            "and s.cotton_part = :cottonPart", nativeQuery = true)
    List<Socks> findByColorLikeAndCottonPartLike(String color, Short cottonPart);

    @Query(value = "select id, color, cotton_part, quality from Socks s where s.color LIKE :color " +
            "and cotton_part > :cottonPart", nativeQuery = true)
    List<Socks> findByColorLikeAndCottonPartMoreThan(String color, Short cottonPart);

    @Query(value = "select id, color, cotton_part, quality from Socks s where s.color LIKE :color " +
            "and cotton_part < :cottonPart", nativeQuery = true)
    List<Socks> findByColorLikeAndCottonPartLessThan(String color, Short cottonPart);

    List<Socks> findByColorLike(String color);
}
