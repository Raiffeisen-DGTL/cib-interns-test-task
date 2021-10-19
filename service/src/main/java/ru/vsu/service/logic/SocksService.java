package ru.vsu.service.logic;

import ru.vsu.service.model.SocksDto;

public interface SocksService {

  Integer findQuantityByParams(String color, String operation, Integer cottonPart);

  SocksDto addSocks( SocksDto dto);

  SocksDto removeSocks(SocksDto dto);
}
