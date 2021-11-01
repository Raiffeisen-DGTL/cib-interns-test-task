package ru.danilarassokhin.raiffeisensocks.mapper;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.danilarassokhin.raiffeisensocks.dto.SocksIncomeDto;
import ru.danilarassokhin.raiffeisensocks.model.Socks;
import ru.danilarassokhin.raiffeisensocks.service.dto.SocksServiceIncomeDto;

public class SocksMapperTest {

  @Test
  public void socksIncomeDtoToSocks() {
    SocksServiceIncomeDto socksIncomeDto = new SocksServiceIncomeDto("red", (byte) 10, 1L);

    Socks model = SocksMapper.INSTANCE.incomeDtoToSocks(socksIncomeDto);

    Assertions.assertEquals(socksIncomeDto.getColor(), model.getColor());
    Assertions.assertEquals(socksIncomeDto.getCottonPart(), model.getCottonPart());
    Assertions.assertEquals(socksIncomeDto.getQuantity(), model.getQuantity());
  }

  @Test
  public void controllerIncomeDtoToService() {
    SocksIncomeDto socksIncomeDto = new SocksIncomeDto("red", (byte) 10, 1L);
    SocksServiceIncomeDto socksServiceIncomeDto = SocksMapper.INSTANCE.controllerIncomeDtoToServiceDto(socksIncomeDto);

    Assertions.assertEquals(socksIncomeDto.getQuantity(), socksServiceIncomeDto.getQuantity());
    Assertions.assertEquals(socksIncomeDto.getColor(), socksServiceIncomeDto.getColor());
    Assertions.assertEquals(socksIncomeDto.getCottonPart(), socksServiceIncomeDto.getCottonPart());
  }

}
