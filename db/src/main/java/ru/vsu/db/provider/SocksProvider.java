package ru.vsu.db.provider;

import ru.vsu.db.entity.Socks;
import ru.vsu.db.entity.SocksId;

import java.util.Optional;

public interface SocksProvider {

  Optional<Socks> findById(String color, Integer cottonPart);

  Socks save(Socks entity);

  Integer sumOfQuantityByColorAndCottonPartGreaterThan(String color, Integer cottonPart);

  Integer sumOfQuantityByColorAndCottonPartLessThan(String color, Integer cottonPart);
}
