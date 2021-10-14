package com.mn000009.warehouse.controller;

import com.mn000009.warehouse.controller.dto.SocksDto;
import com.mn000009.warehouse.exception.IllegalColorException;
import com.mn000009.warehouse.exception.IllegalCottonPartException;
import com.mn000009.warehouse.exception.IllegalQuantityException;
import com.mn000009.warehouse.exception.IncorrectOperationException;
import com.mn000009.warehouse.service.SocksService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@SpringBootTest(classes = SocksController.class)
@DisplayName("SocksPackage Controller Test")
class SocksControllerTest {

  public static final int COTTON_PART = 50;
  public static final int INCORRECT_COTTON_PART = 150;
  public static final int INCORRECT_COTTON_PART2 = -10;
  public static final String COLOR_TITLE = "red";
  public static final String INCORRECT_COLOR_TITLE = "";
  public static final String INCORRECT_COLOR_TITLE2 = null;
  public static final int QUANTITY = 5;
  public static final int INCORRECT_QUANTITY = 0;
  public static final int INCORRECT_QUANTITY2 = -2;
  public static final String OPERATION = "moreThan";
  public static final String INCORRECT_OPERATION = "fas";

  @Autowired
  private SocksController controller;
  @MockBean
  private SocksService service;

  @Test
  void shouldContextLoads() {
    assertThat(controller).isNotNull();
    assertThat(service).isNotNull();
  }

  @Test
  @DisplayName("Should_ThrowException_When_IncomeParamIsIncorrect")
  void doIncome() {
    assertThrows(IllegalQuantityException.class,
        () -> controller.doIncome(new SocksDto(COLOR_TITLE, COTTON_PART, INCORRECT_QUANTITY)));
    assertThrows(IllegalQuantityException.class,
        () -> controller.doIncome(new SocksDto(COLOR_TITLE, COTTON_PART, INCORRECT_QUANTITY2)));
    assertThrows(IllegalCottonPartException.class,
        () -> controller.doIncome(new SocksDto(COLOR_TITLE, INCORRECT_COTTON_PART, QUANTITY)));
    assertThrows(IllegalCottonPartException.class,
        () -> controller.doIncome(new SocksDto(COLOR_TITLE, INCORRECT_COTTON_PART2, QUANTITY)));
    assertThrows(IllegalColorException.class,
        () -> controller.doIncome(new SocksDto(INCORRECT_COLOR_TITLE, COTTON_PART, QUANTITY)));
    assertThrows(IllegalColorException.class,
        () -> controller.doIncome(new SocksDto(INCORRECT_COLOR_TITLE2, COTTON_PART, QUANTITY)));
    verify(service, times(0)).income(any());
  }

  @Test
  @DisplayName("Should_ThrowException_When_OutcomeParamIsIncorrect")
  void doOutcome() {
    assertThrows(IllegalQuantityException.class,
        () -> controller.doOutcome(new SocksDto(COLOR_TITLE, COTTON_PART, INCORRECT_QUANTITY)));
    assertThrows(IllegalQuantityException.class,
        () -> controller.doOutcome(new SocksDto(COLOR_TITLE, COTTON_PART, INCORRECT_QUANTITY2)));
    assertThrows(IllegalCottonPartException.class,
        () -> controller.doOutcome(new SocksDto(COLOR_TITLE, INCORRECT_COTTON_PART, QUANTITY)));
    assertThrows(IllegalCottonPartException.class,
        () -> controller.doOutcome(new SocksDto(COLOR_TITLE, INCORRECT_COTTON_PART2, QUANTITY)));
    assertThrows(IllegalColorException.class,
        () -> controller.doOutcome(new SocksDto(INCORRECT_COLOR_TITLE, COTTON_PART, QUANTITY)));
    assertThrows(IllegalColorException.class,
        () -> controller.doOutcome(new SocksDto(INCORRECT_COLOR_TITLE2, COTTON_PART, QUANTITY)));
    verify(service, times(0)).income(any());
  }

  @Test
  @DisplayName("Should_ThrowException_When_StatusParamIsIncorrect")
  void doStatus() {
    assertThrows(IncorrectOperationException.class,
        () -> controller.doStatus(COLOR_TITLE, INCORRECT_OPERATION, COTTON_PART));

    assertThrows(IllegalCottonPartException.class,
        () -> controller.doStatus(COLOR_TITLE, OPERATION, INCORRECT_COTTON_PART));
    assertThrows(IllegalCottonPartException.class,
        () -> controller.doStatus(COLOR_TITLE, OPERATION, INCORRECT_COTTON_PART2));
    assertThrows(IllegalColorException.class,
        () -> controller.doStatus(INCORRECT_COLOR_TITLE, OPERATION, COTTON_PART));
    assertThrows(IllegalColorException.class,
        () -> controller.doStatus(INCORRECT_COLOR_TITLE2, OPERATION, COTTON_PART));
  }

}