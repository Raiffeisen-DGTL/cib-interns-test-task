package ru.raiffeisen.cibinternstesttask.socks;

import java.util.List;
import java.util.Optional;
import org.springframework.data.repository.CrudRepository;

public interface SocksRepository extends CrudRepository<Socks, Long> {

    Optional<Socks> findSocksByColorAndCottonPart(Color color, Short cottonPart);

    List<Socks> findSocksByColorAndCottonPartGreaterThan(Color color, Short cottonPart);

    List<Socks> findSocksByColorAndCottonPartLessThan(Color color, Short cottonPart);

}
