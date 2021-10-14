package ru.danilarassokhin.raiffeisensocks.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.danilarassokhin.raiffeisensocks.model.Socks;
import ru.danilarassokhin.raiffeisensocks.model.SocksId;

import java.util.Optional;

/**
 * Repository to perform operations with {@link ru.danilarassokhin.raiffeisensocks.model.Socks}
 */
@Repository
public interface SocksRepository extends JpaRepository<Socks, SocksId> {

    /**
     * Gets socks for given color and cotton part
     * @param color Socks color
     * @param cottonPart Socks cotton part
     * @return Optional socks count for given condition
     */
    Optional<Socks> findByColorAndCottonPartIs(String color, byte cottonPart);

}
