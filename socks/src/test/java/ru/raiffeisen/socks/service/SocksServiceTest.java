package ru.raiffeisen.socks.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import ru.raiffeisen.socks.entity.Color;
import ru.raiffeisen.socks.entity.Socks;
import ru.raiffeisen.socks.exception.ColorNotFoundException;
import ru.raiffeisen.socks.exception.NotEnoughSocksException;
import ru.raiffeisen.socks.exception.OperationUnknown;
import ru.raiffeisen.socks.exception.SocksNotFoundException;
import ru.raiffeisen.socks.repository.ColorRepository;
import ru.raiffeisen.socks.repository.SocksRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;


@SpringBootTest
class SocksServiceTest {

    @Autowired
    private SocksService socksService;

    @MockBean
    private SocksRepository socksRepositoryMock;

    @MockBean
    private ColorRepository colorRepositoryMock;

    private final int COTTON_PART = 25;
    private final long QUANTITY = 100;
    private final long INCOME_QUANTITY = 10;
    private final long OUTCOME_QUANTITY_LESS_THEN_QUANTITY = 80;
    private final long OUTCOME_QUANTITY_GREATER_THEN_QUANTITY = 150;
    private final String COLOR = "red";

    private final String OPERATION_LESS_THAN = "lessThan";
    private final String OPERATION_MORE_THAN = "moreThan";
    private final String OPERATION_EQUAL = "equal";
    private final String OPERATION_UNKNOWN = "qwerty";

    @Test
    void income_whenSocksPresented() {
        Optional<Socks> socks = Optional.of(new Socks(1L, COTTON_PART, QUANTITY, new Color(1L, COLOR)));
        doReturn(socks).when(socksRepositoryMock).findByCottonPartAndColorName(COTTON_PART, COLOR);
        socksService.income(COLOR, COTTON_PART, INCOME_QUANTITY);
        verify(socksRepositoryMock, times(1)).findByCottonPartAndColorName(COTTON_PART, COLOR);
        assertEquals(QUANTITY + INCOME_QUANTITY, socks.get().getQuantity());
        verify(socksRepositoryMock, times(1)).save(socks.get());
    }

    @Test
    void income_whenNotSocksPresented_thenColorFound() {
        Optional<Color> color = Optional.of(new Color(1L, COLOR));
        doReturn(color).when(colorRepositoryMock).findByName(COLOR);
        socksService.income(COLOR, COTTON_PART, INCOME_QUANTITY);
        verify(socksRepositoryMock, times(1)).findByCottonPartAndColorName(COTTON_PART, COLOR);
    }

    @Test
    void income_whenNotSocksPresented_thenColorNotFound() {
        Assertions.assertThrows(ColorNotFoundException.class,
                () -> socksService.income(COLOR, COTTON_PART, INCOME_QUANTITY));
    }

    @Test
    void outcome_whenSocksNotFound() {
        Assertions.assertThrows(SocksNotFoundException.class,
                () -> socksService.outcome(COLOR, COTTON_PART, INCOME_QUANTITY));
    }

    @Test
    void outcome_whenSocksFound_neededQuantityLessThenStored() {
        Optional<Socks> socks = Optional.of(new Socks(1L, COTTON_PART, QUANTITY, new Color(1L, COLOR)));
        doReturn(socks).when(socksRepositoryMock).findByCottonPartAndColorName(COTTON_PART, COLOR);
        socksService.outcome(COLOR, COTTON_PART, OUTCOME_QUANTITY_LESS_THEN_QUANTITY);
        assertEquals(QUANTITY - OUTCOME_QUANTITY_LESS_THEN_QUANTITY, socks.get().getQuantity());
        verify(socksRepositoryMock, times(1)).save(socks.get());
    }

    @Test
    void outcome_whenSocksFound_neededQuantityEqualStored() {
        Optional<Socks> socks = Optional.of(new Socks(1L, COTTON_PART, QUANTITY, new Color(1L, COLOR)));
        doReturn(socks).when(socksRepositoryMock).findByCottonPartAndColorName(COTTON_PART, COLOR);
        socksService.outcome(COLOR, COTTON_PART, QUANTITY);
        assertEquals(0, socks.get().getQuantity());
        verify(socksRepositoryMock, times(1)).save(socks.get());
    }

    @Test
    void outcome_whenSocksFound_neededQuantityGreaterThenStored() {
        Optional<Socks> socks = Optional.of(new Socks(1L, COTTON_PART, QUANTITY, new Color(1L, COLOR)));
        doReturn(socks).when(socksRepositoryMock).findByCottonPartAndColorName(COTTON_PART, COLOR);
        Assertions.assertThrows(NotEnoughSocksException.class,
                () -> socksService.outcome(COLOR, COTTON_PART, OUTCOME_QUANTITY_GREATER_THEN_QUANTITY));
    }

    @Test
    void socksLessThen() {
        socksService.socks(COLOR, OPERATION_LESS_THAN, COTTON_PART);
        verify(socksRepositoryMock, times(1))
                .findByColorNameAndCottonPartLessThan(COLOR, COTTON_PART);
    }

    @Test
    void socksEqual() {
        socksService.socks(COLOR, OPERATION_EQUAL, COTTON_PART);
        verify(socksRepositoryMock, times(1))
                .findByColorNameAndCottonPartEquals(COLOR, COTTON_PART);
    }

    @Test
    void socksMoreThen() {
        socksService.socks(COLOR, OPERATION_MORE_THAN, COTTON_PART);
        verify(socksRepositoryMock, times(1))
                .findByColorNameAndCottonPartGreaterThan(COLOR, COTTON_PART);
    }

    @Test
    void socksUnknownOperation() {
        Assertions.assertThrows(OperationUnknown.class,
                () -> socksService.socks(COLOR, OPERATION_UNKNOWN, COTTON_PART));
    }

    @Test
    void socks() {
        List<Socks> socksList = new ArrayList<>();
        socksList.add(new Socks(1L, COTTON_PART, QUANTITY, new Color(1L, COLOR)));
        socksList.add(new Socks(2L, COTTON_PART, INCOME_QUANTITY, new Color(1L, COLOR)));
        socksList.add(new Socks(3L, COTTON_PART, OUTCOME_QUANTITY_LESS_THEN_QUANTITY, new Color(1L, COLOR)));
        doReturn(socksList).when(socksRepositoryMock).findByColorNameAndCottonPartEquals(COLOR, COTTON_PART);
        assertEquals(QUANTITY + INCOME_QUANTITY + OUTCOME_QUANTITY_LESS_THEN_QUANTITY,
                socksService.socks(COLOR, OPERATION_EQUAL, COTTON_PART));
        verify(socksRepositoryMock, times(1))
                .findByColorNameAndCottonPartEquals(COLOR, COTTON_PART);
    }
}