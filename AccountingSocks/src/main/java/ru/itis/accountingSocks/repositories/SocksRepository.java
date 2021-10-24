package ru.itis.accountingSocks.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.itis.accountingSocks.models.Socks;

public interface SocksRepository extends JpaRepository<Socks, Long> {

    Socks findByColorAndCottonPart(String color, Integer cottonPart);

    @Query(value = "SELECT sum(s.quantity) FROM SOCKS s where s.color= :color AND s.cotton_part> :cottonPart",nativeQuery = true)
    int getQuantitySocksByColorEqualAndCottonPartMoreThan(String color, int cottonPart);

    @Query(value = "SELECT sum(s.quantity) FROM SOCKS s where s.color= :color AND s.cotton_part< :cottonPart",nativeQuery = true)
    int getQuantitySocksByColorEqualAndCottonPartLessThan(String color, int cottonPart);

    @Query(value = "SELECT sum(s.quantity) FROM SOCKS s where s.color= :color AND s.cotton_part= :cottonPart",nativeQuery = true)
    int getQuantitySocksByColorEqualAndCottonPartEqual(String color, int cottonPart);
}
