package com.github.furetur.raiffeisentask.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.github.furetur.raiffeisentask.db.Socks;
import com.github.furetur.raiffeisentask.db.SocksRepository;
import com.github.furetur.raiffeisentask.restData.OperationType;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

@SpringBootTest
class SocksServiceTest {

  @InjectMocks @Autowired private SocksServiceImpl socksService;
  @MockBean private SocksRepository socksRepository;

  @Captor private ArgumentCaptor<Socks> socksCaptor;

  @Test
  void shouldCountAllBlackSocks() {
    when(socksRepository.countByColorAndCottonPartGreaterThan("black", 1)).thenReturn(11);

    var count = socksService.countSocks("black", OperationType.MORE_THAN, 1);
    assertEquals(11, count);
  }

  @Test
  void shouldOnlyCountHighBlackSocks() {
    when(socksRepository.countByColorAndCottonPartGreaterThan("black", 30)).thenReturn(10);

    var count = socksService.countSocks("black", OperationType.MORE_THAN, 30);
    assertEquals(10, count);
  }

  @Test
  void shouldCountNoSocks() {
    when(socksRepository.countByColorAndCottonPartGreaterThan("black", 50)).thenReturn(0);

    var count = socksService.countSocks("black", OperationType.MORE_THAN, 50);
    assertEquals(0, count);
  }

  @Test
  void shouldCountOnlyLowSocks() {
    when(socksRepository.countByColorAndCottonPartLessThan("black", 50)).thenReturn(1);

    var count = socksService.countSocks("black", OperationType.LESS_THAN, 50);
    assertEquals(1, count);
  }

  @Test
  void shouldCountExactlyWhiteSocks() {
    when(socksRepository.countByColorAndCottonPart("white", 80)).thenReturn(100);

    var count = socksService.countSocks("white", OperationType.EQUAL, 80);
    assertEquals(100, count);
  }

  @Test
  void removedSocksShouldNotBeCounted() {
    var whiteSocks = new Socks("white", 80, 100);
    when(socksRepository.findByColorAndCottonPart("white", 80)).thenReturn(whiteSocks);

    socksService.removeSocks("white", 80, 10);

    verify(socksRepository, times(1)).save(socksCaptor.capture());
    var newSocks = socksCaptor.getValue();
    assertEquals("white", newSocks.getColor());
    assertEquals(80, newSocks.getCottonPart());
    assertEquals(90, newSocks.getQuantity());
  }
}
