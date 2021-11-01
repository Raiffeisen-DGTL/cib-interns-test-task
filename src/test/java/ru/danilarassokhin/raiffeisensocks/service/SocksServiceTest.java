package ru.danilarassokhin.raiffeisensocks.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.danilarassokhin.raiffeisensocks.repository.SocksRepository;
import ru.danilarassokhin.raiffeisensocks.service.dto.*;
import ru.danilarassokhin.raiffeisensocks.service.impl.SocksServiceImpl;

@ExtendWith(MockitoExtension.class)
public class SocksServiceTest {

  private static SocksServiceImpl socksService;

  @BeforeAll
  public static void init() {
    SocksRepository socksRepository = Mockito.mock(SocksRepository.class);
    socksService = new SocksServiceImpl(socksRepository);
  }

  @Test
  public void testInvalidIncome() {
    SocksServiceIncomeDto socksIncomeDto = new SocksServiceIncomeDto("redd", (byte) -1, -1L);
    SocksServiceResponse socksServiceResponse = socksService.income(socksIncomeDto);

    Assertions.assertEquals(ServiceResponseStatus.INVALID_DATA, socksServiceResponse.getStatus());
  }

  @Test
  public void testInvalidOutcome() {
    SocksServiceOutcomeDto socksOutcomeDto = new SocksServiceOutcomeDto("red", (byte) -1, -1L);
    SocksServiceResponse socksServiceResponse = socksService.outcome(socksOutcomeDto);

    Assertions.assertEquals(ServiceResponseStatus.INVALID_DATA, socksServiceResponse.getStatus());
  }

  @Test
  public void testInvalidOperationInSocksCount() {
    SocksServiceSearchDto socksSearchDto = new SocksServiceSearchDto("red", "equals", (byte) 10);

    SocksServiceResponse socksServiceResponse = socksService.countSocks(socksSearchDto);
    Assertions.assertEquals(ServiceResponseStatus.INVALID_DATA, socksServiceResponse.getStatus());
  }
}
