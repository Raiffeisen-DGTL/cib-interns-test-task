package ru.danilarassokhin.raiffeisensocks.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.danilarassokhin.raiffeisensocks.model.Socks;
import ru.danilarassokhin.raiffeisensocks.model.SocksColor;
import ru.danilarassokhin.raiffeisensocks.model.SocksId;

import java.util.List;
import java.util.Set;

@Repository
public interface SocksRepository extends JpaRepository<Socks, SocksId> {

    List<Socks> findBySocksColorAndCottonPartGreaterThan(SocksColor color, byte cottonPart);
    List<Socks> findBySocksColorAndCottonPartLessThan(SocksColor color, byte cottonPart);
    List<Socks> findBySocksColorAndCottonPartIs(SocksColor color, byte cottonPart);

}
