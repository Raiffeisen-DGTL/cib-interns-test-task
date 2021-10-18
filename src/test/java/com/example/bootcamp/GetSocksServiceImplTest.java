package com.example.bootcamp;

import com.example.bootcamp.repo.SocksRepo;
import com.example.bootcamp.service.GetSocksService;
import com.example.bootcamp.service.GetSocksServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class GetSocksServiceImplTest {

    /**
     * Цвет имеющихся в базе пар носков.
     */
    static final String EXISTENT = "red".toUpperCase();

    /**
     * Цвет отсутствующих в базе пар носков.
     */

    static final String NON_EXISTENT = "Non-Existent";

    /**
     * Содержание хлопка в носках,имеющиеся в базе данных
     */
    static final int COTTON_EXISTENT = 20;

    /**
     * Операторы сравнения доли хлопка в носках:
     * moreThan - больше
     * equal - равно
     * lessThan - меньше
     */
    static final String moreThan = "MoreThan";
    static final String equal = "EQUAL";
    static final String lessThan = "LessTHAN";

    GetSocksService getSocksService;

    SocksRepo socksRepo;

    @BeforeEach
    void setup() {
        socksRepo = mock(SocksRepo.class);

        when(socksRepo.findSocksMoreThan(EXISTENT, COTTON_EXISTENT)).thenReturn(55);

        when(socksRepo.findSocksEqual(EXISTENT, COTTON_EXISTENT)).thenReturn(20);

        when(socksRepo.findSocksLessThan(EXISTENT, COTTON_EXISTENT)).thenReturn(15);

        when(socksRepo.findSocksMoreThan(NON_EXISTENT, COTTON_EXISTENT)).thenReturn(0);

        when(socksRepo.findSocksEqual(NON_EXISTENT, COTTON_EXISTENT)).thenReturn(0);

        when(socksRepo.findSocksLessThan(NON_EXISTENT, COTTON_EXISTENT)).thenReturn(0);

        getSocksService = new GetSocksServiceImpl(socksRepo);
    }

    @Test
    void checkRequestExistentMoreThan() {
        int socks = getSocksService.getSocks(EXISTENT, moreThan, COTTON_EXISTENT);

        assertEquals(55, socks);
    }

    @Test
    void checkRequestExistentEqual() {
        int socks = getSocksService.getSocks(EXISTENT, equal, COTTON_EXISTENT);

        assertEquals(20, socks);
    }

    @Test
    void checkRequestExistentLessThan() {
        int socks = getSocksService.getSocks(EXISTENT, lessThan, COTTON_EXISTENT);

        assertEquals(15, socks);
    }

    @Test
    void checkRequestNonExistentOperation() {
        Assertions.assertThrows(
                RuntimeException.class,
                () -> getSocksService.getSocks(EXISTENT, "Test", COTTON_EXISTENT)
        );
    }

    @Test
    void shouldThrowExceptionWhenArgContainsNegativeCotton() {
        Assertions.assertThrows(
                RuntimeException.class,
                () -> getSocksService.getSocks(EXISTENT, lessThan, -55));
    }

    @Test
    void shouldThrowExceptionWhenArgContainsCottonMore100() {
        Assertions.assertThrows(
                RuntimeException.class,
                () -> getSocksService.getSocks(EXISTENT, moreThan, 222));
    }

    @Test
    void checkRequestNonExistentMoreThan() {
        int socks = getSocksService.getSocks(NON_EXISTENT, moreThan, COTTON_EXISTENT);

        assertEquals(0, socks);
    }

    @Test
    void checkRequestNonExistentEqual() {
        int socks = getSocksService.getSocks(NON_EXISTENT, equal, COTTON_EXISTENT);

        assertEquals(0, socks);
    }

    @Test
    void checkRequestNonExistentLessThan() {
        int socks = getSocksService.getSocks(NON_EXISTENT, lessThan, COTTON_EXISTENT);

        assertEquals(0, socks);
    }
}
