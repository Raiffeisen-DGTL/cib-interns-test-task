package ru.project.raiffeisenbank.repos;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import ru.project.raiffeisenbank.entity.Socks;

import java.util.Optional;

public interface SocksRepository extends CrudRepository<Socks, Long> {
    @Query("from Socks where color = :color and cottonPart = :cottonPart")
    Optional<Socks> getSocksByColorAndCottonPart(String color, int cottonPart);

    @Query("select sum(quantity) from Socks where color = :color and cottonPart > :cottonPart")
    Long getSocksMoreThan(String color, int cottonPart);

    @Query("select sum(quantity) from Socks where color = :color and cottonPart < :cottonPart")
    Long getSocksLessThan(String color, int cottonPart);

    @Query("select sum(quantity) from Socks where color = :color and cottonPart = :cottonPart")
    Long getSocksEqual(String color, int cottonPart);
}
