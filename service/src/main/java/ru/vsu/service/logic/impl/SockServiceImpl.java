package ru.vsu.service.logic.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.vsu.db.entity.Socks;
import ru.vsu.db.provider.SocksProvider;
import ru.vsu.service.logic.SocksService;
import ru.vsu.service.mapper.SocksMapper;
import ru.vsu.service.model.SocksDto;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.function.BiFunction;

@Service
public class SockServiceImpl implements SocksService {

  private final SocksProvider provider;
  private final SocksMapper mapper;
  private Map<String, BiFunction<String, Integer, Integer>> providerFunctions;

  @Autowired
  public SockServiceImpl(SocksProvider provider, SocksMapper mapper) {
    this.provider = provider;
    this.mapper = mapper;
  }

  @PostConstruct
  public void postConstruct() {
    Socks defaultQuantity = new Socks();
    defaultQuantity.setQuantity(0);
    providerFunctions = new HashMap<>() {{
      put("equal", (color, cottonPart) -> provider.findById(color, cottonPart)
        .orElse(defaultQuantity).getQuantity());
      put("moreThan", provider::sumOfQuantityByColorAndCottonPartGreaterThan);
      put("lessThan", provider::sumOfQuantityByColorAndCottonPartLessThan);
    }};
  }

  @Override
  public Integer findQuantityByParams(String color, String operation, Integer cottonPart) {
    return Optional.ofNullable(providerFunctions.get(operation).apply(color, cottonPart))
      .orElse(0);
  }

  @Override
  @Transactional
  public SocksDto addSocks(SocksDto dto) {
    Optional<Socks> socks = provider.findById(dto.getColor(), dto.getCottonPart());
    socks.ifPresent(value -> dto.setQuantity(dto.getQuantity() + value.getQuantity()));
    return Optional.of(dto)
      .map(mapper::toEntity)
      .map(provider::save)
      .map(mapper::fromEntity).orElseThrow();
  }

  @Override
  @Transactional
  public SocksDto removeSocks(SocksDto dto) {
    Optional<Socks> socks = provider.findById(dto.getColor(), dto.getCottonPart());
    if (socks.isPresent()) {
      dto.setQuantity(socks.get().getQuantity() - dto.getQuantity());
      if (dto.getQuantity() < 0)
        throw new RuntimeException("There are only " + socks.get().getQuantity() + " available pairs");
    } else {
      throw new RuntimeException(" There aren't socks with color: " + dto.getColor()
        + " and cottonPart: " + dto.getCottonPart());
    }
    return Optional.of(dto)
      .map(mapper::toEntity)
      .map(provider::save)
      .map(mapper::fromEntity).orElseThrow(() -> new RuntimeException(" There aren't socks with color: " + dto.getColor()
        + " and cottonPart: " + dto.getCottonPart()));
  }
}
