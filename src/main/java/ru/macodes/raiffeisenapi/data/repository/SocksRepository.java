package ru.macodes.raiffeisenapi.data.repository;

import org.springframework.data.repository.CrudRepository;
import ru.macodes.raiffeisenapi.data.entity.Socks;

import java.util.List;
import java.util.Optional;

public interface SocksRepository extends CrudRepository<Socks, Integer> {
    Optional<Socks> getSocksByColorAndCottonPart(String color, byte cottonPart);
    List<Socks> findAllByCottonPartIsGreaterThan(byte cottonPart);
    List<Socks> findAllByCottonPartIsLessThan(byte cottonPart);
    List<Socks> findAllByCottonPartIs(byte cottonPart);
}
