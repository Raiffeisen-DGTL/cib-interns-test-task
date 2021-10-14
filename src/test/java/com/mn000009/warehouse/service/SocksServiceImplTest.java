package com.mn000009.warehouse.service;

import com.mn000009.warehouse.controller.dto.SocksDto;
import com.mn000009.warehouse.domain.Color;
import com.mn000009.warehouse.domain.Operation;
import com.mn000009.warehouse.domain.SocksPackage;
import com.mn000009.warehouse.exception.NotEnoughSuchSocksException;
import com.mn000009.warehouse.repository.SocksRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@SpringBootTest(classes = SocksServiceImpl.class)
@DisplayName("SocksPackage Service Test")
class SocksServiceImplTest {

  public static final int COTTON_PART = 50;
  public static final String COLOR_TITLE = "red";
  public static final int QUANTITY = 5;
  public static final int QUANTITY_LESSER = 3;
  public static final int QUANTITY_BIGGER = 7;
  public static final int QUANTITY_RESULT = 2;
  public static final String EXPECTED_MESSAGE = "SocksPackage were not found in the right volume.\nThere are only 7 socks in stock";
  public static final Long ID = 1L;
  public static final SocksDto SOCKS_DTO = new SocksDto(COLOR_TITLE, COTTON_PART, QUANTITY);

  @Autowired
  private SocksService service;
  @MockBean
  private SocksRepository repository;

  @Test
  @DisplayName("Should_IncomeSocks_When_SocksNotExist")
  void incomeFirst() {
    when(repository.findSocksByColor_TitleAndCottonPartEquals(COLOR_TITLE, COTTON_PART))
        .thenReturn(Optional.empty());
    service.income(SOCKS_DTO);
    verify(repository, times(1))
        .save(new SocksPackage(new Color(COLOR_TITLE), COTTON_PART, QUANTITY));
  }

  @Test
  @DisplayName("Should_IncomeSocksThroughtIncreaseQuantity_When_SocksExist")
  void incomeSecond() {
    when(repository.findSocksByColor_TitleAndCottonPartEquals(COLOR_TITLE, COTTON_PART))
        .thenReturn(Optional.of(new SocksPackage(ID, new Color(ID, COLOR_TITLE), COTTON_PART, QUANTITY)));
    service.income(SOCKS_DTO);
    verify(repository, times(1))
        .save(new SocksPackage(ID, new Color(ID, COLOR_TITLE), COTTON_PART, QUANTITY * 2));
  }

  @Test
  @DisplayName("Should_OutcomeSocksThroughReduceQuantity_When_SocksQuantityBigger")
  void outcomeFirst() {
    when(repository.findSocksByColor_TitleAndCottonPartEqualsAndQuantityGreaterThanEqual(COLOR_TITLE, COTTON_PART,
        QUANTITY_BIGGER)).thenReturn(Optional.empty());
    Exception exception = assertThrows(NotEnoughSuchSocksException.class,
        () -> service.outcome(new SocksDto(COLOR_TITLE, COTTON_PART, QUANTITY_BIGGER)));
    String actualMessage = exception.getMessage();
    assertEquals(EXPECTED_MESSAGE, actualMessage);
    verify(repository, times(0))
        .save(new SocksPackage(ID, new Color(ID, COLOR_TITLE), COTTON_PART, QUANTITY));
  }

  @Test
  @DisplayName("Should_OutcomeSocksThroughReduceQuantity_When_SocksQuantityEqual")
  void outcomeSecond() {
    when(repository.findSocksByColor_TitleAndCottonPartEqualsAndQuantityGreaterThanEqual(COLOR_TITLE, COTTON_PART
        , QUANTITY)).thenReturn(Optional.of(new SocksPackage(ID, new Color(ID, COLOR_TITLE), COTTON_PART, QUANTITY)));
    service.outcome(new SocksDto(COLOR_TITLE, COTTON_PART, QUANTITY));
    verify(repository, times(1))
        .save(new SocksPackage(ID, new Color(ID, COLOR_TITLE), COTTON_PART, 0));
  }

  @Test
  @DisplayName("Should_OutcomeSocksThroughReduceQuantity_When_SocksQuantityLesser")
  void outcomeThird() {
    when(repository.findSocksByColor_TitleAndCottonPartEqualsAndQuantityGreaterThanEqual(COLOR_TITLE, COTTON_PART
        , QUANTITY_LESSER)).thenReturn(Optional.of(new SocksPackage(ID, new Color(ID, COLOR_TITLE), COTTON_PART, QUANTITY)));
    service.outcome(new SocksDto(COLOR_TITLE, COTTON_PART, QUANTITY_LESSER));
    verify(repository, times(1))
        .save(new SocksPackage(ID, new Color(ID, COLOR_TITLE), COTTON_PART, QUANTITY_RESULT));
  }

  @Test
  @DisplayName("Should_GetNotExistStatus_When_OperationMoreThan")
  void statusFirst() {
    when(repository.findAllByColor_TitleAndCottonPartGreaterThan(COLOR_TITLE, COTTON_PART))
        .thenReturn(Optional.empty());
    assertEquals("0", service.getStatus(COLOR_TITLE, Operation.MORETHAN, COTTON_PART));
    verify(repository, times(1))
        .findAllByColor_TitleAndCottonPartGreaterThan(COLOR_TITLE, COTTON_PART);
  }

  @Test
  @DisplayName("Should_GetNotExistStatus_When_OperationLessThan")
  void statusSecond() {
    when(repository.findAllByColor_TitleAndCottonPartLessThan(COLOR_TITLE, COTTON_PART))
        .thenReturn(Optional.empty());
    assertEquals("0", service.getStatus(COLOR_TITLE, Operation.LESSTHAN, COTTON_PART));
    verify(repository, times(1))
        .findAllByColor_TitleAndCottonPartLessThan(COLOR_TITLE, COTTON_PART);
  }

  @Test
  @DisplayName("Should_GetExistStatus_When_OperationEqual")
  void statusThird() {
    when(repository.findAllByColor_TitleAndCottonPartEquals(COLOR_TITLE, COTTON_PART))
        .thenReturn(Optional.of(5));
    assertEquals("5", service.getStatus(COLOR_TITLE, Operation.EQUAL, COTTON_PART));
    verify(repository, times(1))
        .findAllByColor_TitleAndCottonPartEquals(COLOR_TITLE, COTTON_PART);
  }

  @Test
  @DisplayName("Should_GetNotExistStatus_When_OperationEqual")
  void statusFourth() {
    when(repository.findAllByColor_TitleAndCottonPartEquals(COLOR_TITLE, COTTON_PART))
        .thenReturn(Optional.empty());
    assertEquals("0", service.getStatus(COLOR_TITLE, Operation.EQUAL, COTTON_PART));
    verify(repository, times(1))
        .findAllByColor_TitleAndCottonPartEquals(COLOR_TITLE, COTTON_PART);
  }

  @Test
  @DisplayName("Should_GetExistStatus_When_OperationMoreThan")
  void statusFifth() {
    when(repository.findAllByColor_TitleAndCottonPartGreaterThan(COLOR_TITLE, COTTON_PART))
        .thenReturn(Optional.of(5));
    assertEquals("5", service.getStatus(COLOR_TITLE, Operation.MORETHAN, COTTON_PART));
    verify(repository, times(1))
        .findAllByColor_TitleAndCottonPartGreaterThan(COLOR_TITLE, COTTON_PART);
  }

  @Test
  @DisplayName("Should_GetExistStatus_When_OperationLessThan")
  void statusSixth() {
    when(repository.findAllByColor_TitleAndCottonPartLessThan(COLOR_TITLE, COTTON_PART))
        .thenReturn(Optional.of(5));
    assertEquals("5", service.getStatus(COLOR_TITLE, Operation.LESSTHAN, COTTON_PART));
    verify(repository, times(1))
        .findAllByColor_TitleAndCottonPartLessThan(COLOR_TITLE, COTTON_PART);
  }

}