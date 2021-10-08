package ru.danilarassokhin.raiffeisensocks.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
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
     * Counts socks with given color and cotton part condition
     * @param color Socks color to count
     * @param cottonPart Socks cotton part to be minimum
     * @return Optional socks count for given condition
     */
    @Query("SELECT SUM(quantity) FROM socks WHERE color = :color AND cotton_part >= :cottonPart")
    Optional<Long> countByColorAndCottonPartGreaterThan(@Param("color") String color, @Param("cottonPart") byte cottonPart);

    /**
     * Counts socks with given color and cotton part condition
     * @param color Socks color to count
     * @param cottonPart Socks cotton part to be maximum
     * @return Optional socks count for given condition
     */
    @Query("SELECT SUM(quantity) FROM socks WHERE color = :color AND cotton_part <= :cottonPart")
    Optional<Long> countByColorAndCottonPartLessThan(@Param("color") String color, @Param("cottonPart") byte cottonPart);

    /**
     * Gets socks for given color and cotton part
     * @param color Socks color
     * @param cottonPart Socks cotton part
     * @return Optional socks count for given condition
     */
    Optional<Socks> findByColorAndCottonPartIs(String color, byte cottonPart);

}
