package ru.javabootcamp.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.javabootcamp.model.Socks;

import java.util.List;

@Repository
public interface SocksRepository extends CrudRepository<Socks, Long> {

    List<Socks> findAllByColorAndCottonPart(String color, int cottonPart);

    List<Socks> findAllByColorAndCottonPartEquals(String color, int cottonPart);

    List<Socks> findAllByColorAndCottonPartLessThan(String color, int cottonPart);

    List<Socks> findAllByColorAndCottonPartGreaterThan(String color, int cottonPart);

}
