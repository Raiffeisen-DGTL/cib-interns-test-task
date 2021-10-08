package ru.danilarassokhin.raiffeisensocks.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.danilarassokhin.raiffeisensocks.model.Socks;
import ru.danilarassokhin.raiffeisensocks.model.SocksId;

import java.util.Optional;
import java.util.Set;

@Repository
public interface SocksRepository extends JpaRepository<Socks, SocksId> {

    @Query("SELECT SUM(s.quantity) FROM socks s WHERE s.color = :color AND cotton_part >= :cottonPart")
    Long countByColorAndCottonPartGreaterThan(@Param("color") String color, @Param("cottonPart") byte cottonPart);
    @Query("SELECT SUM(s.quantity) FROM socks s WHERE s.color = :color AND cotton_part <= :cottonPart")
    Long countByColorAndCottonPartLessThan(@Param("color") String color, @Param("cottonPart") byte cottonPart);
    Set<Socks> findByColorAndCottonPartGreaterThan(String color, byte cottonPart);
    Set<Socks> findByColorAndCottonPartLessThan(String color, byte cottonPart);
    Optional<Socks> findByColorAndCottonPartIs(String color, byte cottonPart);

}
