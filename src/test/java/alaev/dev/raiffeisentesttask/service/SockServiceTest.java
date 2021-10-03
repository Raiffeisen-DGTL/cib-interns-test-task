package alaev.dev.raiffeisentesttask.service;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import alaev.dev.raiffeisentesttask.domain.Sock;
import alaev.dev.raiffeisentesttask.repository.SockRepository;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

@SpringBootTest(classes = SockService.class)
class SockServiceTest {

  public static final String COLOR_NAME = "green";
  public static final int COTTON_PART = 10;
  public static final int QUANTITY = 20;
  public static final Long ID = 1L;
  @Autowired
  private SockService service;

  @MockBean
  private SockRepository repository;

  @Test
  void shouldSaveOriginalPairColorAndCottonPart() {
    when(repository.findSockByColorAndCottonPart(COLOR_NAME, COTTON_PART))
        .thenReturn(Optional.empty());

    service.addSock(COLOR_NAME, COTTON_PART, QUANTITY);

    verify(repository, times(1))
        .save(new Sock(any(), COLOR_NAME, COTTON_PART, QUANTITY));
  }

  @Test
  void shouldAddQuantityToExistPair() {
    when(repository.findSockByColorAndCottonPart(COLOR_NAME, COTTON_PART))
        .thenReturn(Optional.of(new Sock(ID, COLOR_NAME, COTTON_PART, QUANTITY)));

    service.addSock(COLOR_NAME, COTTON_PART, QUANTITY);

    verify(repository, times(1)).
        save(new Sock(ID, COLOR_NAME, COTTON_PART, 2 * QUANTITY));
  }
}
