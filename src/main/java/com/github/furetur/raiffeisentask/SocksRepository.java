package com.github.furetur.raiffeisentask;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;


public interface SocksRepository extends JpaRepository<Socks, Long> {
    @Query("select coalesce(sum(s.quantity), 0) from Socks s where s.color = ?1 and s.cottonPart > ?2")
    int countByColorAndCottonPartGreaterThan(String color, Integer cottonPart);

    @Query("select coalesce(sum(s.quantity), 0) from Socks s where s.color = ?1 and s.cottonPart < ?2")
    int countByColorAndCottonPartLessThan(String color, Integer cottonPart);

    @Query("select coalesce(sum(s.quantity), 0) from Socks s where s.color = ?1 and s.cottonPart = ?2")
    int countByColorAndCottonPart(String color, Integer cottonPart);

    @Modifying
    @Transactional
    @Query(value = "insert into socks (color, cotton_part, quantity) values (?1, ?2, ?3) on conflict (color, cotton_part) do update set quantity = socks.quantity + ?3", nativeQuery = true)
    void addSocks(String color, int cottonPart, int quantity);
}
