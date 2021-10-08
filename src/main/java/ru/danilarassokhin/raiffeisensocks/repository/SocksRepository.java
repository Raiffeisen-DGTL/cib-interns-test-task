package ru.danilarassokhin.raiffeisensocks.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.danilarassokhin.raiffeisensocks.model.Socks;
import ru.danilarassokhin.raiffeisensocks.model.SocksId;

import java.util.Optional;

@Repository
public interface SocksRepository extends JpaRepository<Socks, SocksId> {

    Optional<Socks> findByColorAndCottonPartGreaterThan(String color, byte cottonPart);
    Optional<Socks> findByColorAndCottonPartLessThan(String color, byte cottonPart);
    Optional<Socks> findByColorAndCottonPartIs(String color, byte cottonPart);

}
