package com.github.furetur.raiffeisentask;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


public interface SocksRepository extends JpaRepository<Socks, Long> {
    @Query("select coalesce(sum(s.quantity), 0) from Socks s where s.color = ?1 and s.cottonPart > ?2")
    int countByColorAndCottonPartGreaterThan(String color, Integer cottonPart);

    @Query("select coalesce(sum(s.quantity), 0) from Socks s where s.color = ?1 and s.cottonPart < ?2")
    int countByColorAndCottonPartLessThan(String color, Integer cottonPart);

    @Query("select coalesce(sum(s.quantity), 0) from Socks s where s.color = ?1 and s.cottonPart = ?2")
    int countByColorAndCottonPart(String color, Integer cottonPart);
}
