package ru.raif.socks.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.raif.socks.model.SocksModel;

import java.util.List;

@Repository
public interface SocksRepository extends JpaRepository<SocksModel, Long> {
    SocksModel findByColorAndCottonPart(String color, int cottonPart);
    List<SocksModel> findByColorAndCottonPartEquals(String color, int cottonPart);
    List<SocksModel> findByColorAndCottonPartGreaterThan(String color, int cottonPart);
    List<SocksModel> findByColorAndCottonPartIsLessThan(String color, int cottonPart);
}
