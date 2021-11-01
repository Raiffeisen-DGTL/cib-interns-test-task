package ru.ikuzin.DGTLTask.Socks.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.ikuzin.DGTLTask.Socks.model.SocksCottonPart;

import java.util.Optional;


@Repository
public interface SocksCottonPartRepository extends CrudRepository<SocksCottonPart, String> {
    Optional<SocksCottonPart> findByPartEquals(Integer part);
}
