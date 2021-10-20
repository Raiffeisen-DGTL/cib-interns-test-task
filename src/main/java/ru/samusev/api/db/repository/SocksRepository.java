package ru.samusev.api.db.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.samusev.api.db.model.Socks;
import ru.samusev.api.constant.SocksColor;

import java.util.Optional;

public interface SocksRepository extends JpaRepository<Socks, Long> {
    Optional<Socks> findAllByColorAndCottonPart(SocksColor color, Integer cottonPart);

    @Query("select sum(s.quantity) from Socks s where s.color = ?1 and s.cottonPart = ?2")
    Optional<Long> getQuantityByColorAndCottonPart(SocksColor color, Integer cottonPart);

    @Query("select sum(s.quantity) from Socks s where s.color = ?1 and s.cottonPart < ?2")
    Optional<Long> getQuantityByColorAndCottonPartLessThan(SocksColor color, Integer cottonPart);

    @Query("select sum(s.quantity) from Socks s where s.color = ?1 and s.cottonPart > ?2")
    Optional<Long> getQuantityByColorAndCottonPartMoreThan(SocksColor color, Integer cottonPart);
}
