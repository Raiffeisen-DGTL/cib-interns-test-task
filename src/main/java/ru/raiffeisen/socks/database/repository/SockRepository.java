package ru.raiffeisen.socks.database.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.raiffeisen.socks.database.entity.Sock;

import java.util.Optional;

@Repository
public interface SockRepository extends CrudRepository<Sock, Integer> {
    Optional<Sock> findByColorAndCottonPart(String color, int cottonPart);

    @Query("SELECT COALESCE(SUM(quantity), 0) FROM Sock WHERE color = :color AND cottonPart < :cottonPart")
    int findQuantitySumByColorAndCottonPartLessThan(String color, int cottonPart);

    @Query("SELECT COALESCE(SUM(quantity), 0) FROM Sock WHERE color = :color AND cottonPart = :cottonPart")
    int findQuantitySumByColorAndCottonPartEquals(String color, int cottonPart);

    @Query("SELECT COALESCE(SUM(quantity), 0) FROM Sock WHERE color = :color AND cottonPart > :cottonPart")
    int findQuantitySumByColorAndCottonPartGreaterThan(String color, int cottonPart);
}
