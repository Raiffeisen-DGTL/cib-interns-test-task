package ru.vsu.db.provider.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.vsu.db.entity.Socks;
import ru.vsu.db.entity.SocksId;
import ru.vsu.db.provider.SocksProvider;
import ru.vsu.db.repository.SocksRepository;

import java.util.Optional;

@Component
public class SocksProviderImpl implements SocksProvider {

  private final SocksRepository repository;

  @Autowired
  public SocksProviderImpl(SocksRepository repository) {
    this.repository = repository;
  }


  @Override
  public Optional<Socks> findById(String color, Integer cottonPart) {
    return repository.findById_ColorAndId_CottonPart(color, cottonPart);
  }

  @Override
  public Socks save(Socks entity) {
    return repository.save(entity);
  }

  @Override
  public Integer sumOfQuantityByColorAndCottonPartGreaterThan(String color, Integer cottonPart) {
    return repository.sumOfQuantityByColorAndCottonPartGreaterThan(color, cottonPart);
  }

  @Override
  public Integer sumOfQuantityByColorAndCottonPartLessThan(String color, Integer cottonPart) {
    return repository.sumOfQuantityByColorAndCottonPartLessThan(color, cottonPart);
  }
}
