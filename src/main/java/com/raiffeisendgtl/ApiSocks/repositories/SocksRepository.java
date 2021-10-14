package com.raiffeisendgtl.ApiSocks.repositories;

import com.raiffeisendgtl.ApiSocks.entities.Socks;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface SocksRepository extends CrudRepository<Socks, Long> {

    Socks findByColorAndCottonPart(String color, int cottonPart);

    @Query(value = "SELECT SUM(quantity * 2) FROM socks WHERE color = :color AND cotton_part < :cottonPart", nativeQuery = true)
    Integer findCountSocksLessThan(@Param("color") String color, @Param("cottonPart") Integer cottonPart);

    @Query(value = "SELECT SUM(quantity * 2) FROM socks WHERE color = :color AND cotton_part = :cottonPart", nativeQuery = true)
    Integer findCountSocksEqual(@Param("color") String color, @Param("cottonPart") Integer cottonPart);

    @Query(value = "SELECT SUM(quantity * 2) FROM socks WHERE color = :color AND cotton_part > :cottonPart", nativeQuery = true)
    Integer findCountSocksMoreThan(@Param("color") String color, @Param("cottonPart") Integer cottonPart);
}
