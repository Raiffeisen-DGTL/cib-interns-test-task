package ru.ikuzin.DGTLTask.Socks.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.ikuzin.DGTLTask.Socks.model.SocksColor;

import java.util.Optional;

@Repository
public interface SocksColorRepository extends CrudRepository<SocksColor, String> {
    Optional<SocksColor> findByColor(String color);
}
