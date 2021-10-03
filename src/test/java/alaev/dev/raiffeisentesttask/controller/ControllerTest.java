package alaev.dev.raiffeisentesttask.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import alaev.dev.raiffeisentesttask.controller.dto.SockDto;
import alaev.dev.raiffeisentesttask.exception.InvalidCottonPartException;
import alaev.dev.raiffeisentesttask.exception.InvalidQuantityException;
import alaev.dev.raiffeisentesttask.service.SockService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;

@Import({Controller.class, SockService.class})
@SpringBootTest
class ControllerTest {

  public static final int INVALID_COTTON_PART = 101;
  public static final int QUANTITY = 0;
  public static final int COTTON_PART = 10;
  public static final int INVALID_QUANTITY = -1;
  @Autowired
  private Controller controller;

  @MockBean
  private SockService service;

  @Test
  void shouldContextLoads() {
    assertThat(controller).isNotNull();
    assertThat(service).isNotNull();
  }

  @Test
  void shouldThrowInvalidCottonPartException() {
    assertThatThrownBy(
        () -> controller.registerArrivalSocks(new SockDto("", INVALID_COTTON_PART, QUANTITY)))
        .isInstanceOf(InvalidCottonPartException.class);

    verify(service, times(0))
        .addSock(any(), any(), any());
  }

  @Test
  void shouldThrowInvalidQuantityException() {
    assertThatThrownBy(
        () -> controller.registerArrivalSocks(new SockDto("", COTTON_PART, INVALID_QUANTITY)))
        .isInstanceOf(InvalidQuantityException.class);

    verify(service, times(0))
        .addSock(any(), any(), any());
  }
}