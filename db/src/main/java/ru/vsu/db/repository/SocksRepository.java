package ru.vsu.db.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.vsu.db.entity.Socks;
import ru.vsu.db.entity.SocksId;

import java.util.Optional;

@Repository
public interface SocksRepository extends JpaRepository<Socks, SocksId> {

  Optional<Socks> findById_ColorAndId_CottonPart(String color, Integer cottonPart);

  Socks save(Socks entity);

  @Query("select sum(e.quantity) from socks e where e.id.color = ?1 and e.id.cottonPart > ?2")
  Integer sumOfQuantityByColorAndCottonPartGreaterThan(String color, Integer cottonPart);

  @Query("select sum(e.quantity) from socks e where e.id.color = ?1 and e.id.cottonPart < ?2")
  Integer sumOfQuantityByColorAndCottonPartLessThan(String color, Integer cottonPart);
}
