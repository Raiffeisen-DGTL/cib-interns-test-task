package ru.raiffeisen.socks.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.raiffeisen.socks.entity.Socks;

import java.util.List;
import java.util.Optional;

@Repository
public interface SocksRepository extends CrudRepository<Socks, Long> {

    Optional<Socks> findByCottonPartAndColorName(int cottonPart, String color);

    List<Socks> findByColorNameAndCottonPartGreaterThan(String color, int cottonPart);

    List<Socks> findByColorNameAndCottonPartLessThan(String color, int cottonPart);

    List<Socks> findByColorNameAndCottonPartEquals(String color, int cottonPart);

}