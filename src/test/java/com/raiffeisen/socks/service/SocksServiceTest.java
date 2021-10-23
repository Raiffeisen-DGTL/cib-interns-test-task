package com.raiffeisen.socks.service;

import com.raiffeisen.socks.dao.SocksRepository;
import com.raiffeisen.socks.dto.SockDto;
import com.raiffeisen.socks.exceptions.IncorrectSockFormatException;
import com.raiffeisen.socks.exceptions.NotFoundSockException;
import com.raiffeisen.socks.model.Sock;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Collections;
import java.util.List;

public class SocksServiceTest {
    private static SocksService socksService;
    private static SocksRepository socksRepositoryMock;
    private static List<Sock> sockList;

    @BeforeAll
    public static void setUp() {
        sockList = List.of(
                new Sock(1L, "green", 43, 130),
                new Sock(2L, "green", 46, 120),
                new Sock(3L, "red", 88, 30)
        );
        socksRepositoryMock = Mockito.mock(SocksRepository.class);
        socksService = new SocksServiceImpl(socksRepositoryMock);
    }

    @Test
    public void registerSockTest() {
        SockDto sockDto = new SockDto(
                sockList.get(2).getColor(),
                sockList.get(2).getCottonPart(),
                32
        );
        Mockito.when(socksRepositoryMock.getSockByColorAndCottonPart(sockDto.getColor(), sockDto.getCottonPart()))
                .thenReturn(java.util.Optional.ofNullable(sockList.get(2)));
        socksService.registerSocks(sockDto);
        Mockito.verify(socksRepositoryMock, Mockito.times(1)).save(sockList.get(2));
    }

    @Test
    public void registerNullTestShouldReturnIncorrectSockFormatException() {
        Assertions.assertThrows(IncorrectSockFormatException.class, () -> {
            socksService.registerSocks(null);
        });
    }

    @Test
    public void registerSockWithNullParamTestShouldReturnIncorrectSockFormatException() {
        Assertions.assertThrows(IncorrectSockFormatException.class, () -> {
            socksService.registerSocks(new SockDto("red", 32, null));
        });
    }

    @Test
    public void registerSockWithIncorrectParamTestShouldReturnIncorrectSockFormatException() {
        Assertions.assertThrows(IncorrectSockFormatException.class, () -> {
            socksService.registerSocks(new SockDto("red", 132, 32));
        });
    }

    @Test
    public void outcomeSockTest() {
        SockDto sockDto = new SockDto(
                sockList.get(1).getColor(),
                sockList.get(1).getCottonPart(),
                32
        );
        Mockito.when(socksRepositoryMock.getSockByColorAndCottonPart(sockDto.getColor(), sockDto.getCottonPart()))
                .thenReturn(java.util.Optional.ofNullable(sockList.get(1)));
        socksService.outcomeSocks(sockDto);
        Mockito.verify(socksRepositoryMock, Mockito.times(1)).save(sockList.get(1));
    }

    @Test
    public void outcomeDeleteSockTest() {
        SockDto sockDto = new SockDto(
                sockList.get(1).getColor(),
                sockList.get(1).getCottonPart(),
                sockList.get(1).getQuantity()
        );
        Mockito.when(socksRepositoryMock.getSockByColorAndCottonPart("green", 46))
                .thenReturn(java.util.Optional.ofNullable(sockList.get(1)));
        socksService.outcomeSocks(sockDto);
        Mockito.verify(socksRepositoryMock, Mockito.times(0)).save(sockList.get(1));
        Mockito.verify(socksRepositoryMock, Mockito.times(1)).delete(sockList.get(1));

    }

    @Test
    public void outcomeSockWithIncorrectParamTestShouldReturnIncorrectSockFormatException() {
        Mockito.when(socksRepositoryMock.getSockByColorAndCottonPart(
                sockList.get(1).getColor(),
                sockList.get(1).getCottonPart()))
                .thenReturn(java.util.Optional.ofNullable(sockList.get(1)));

        Assertions.assertThrows(IncorrectSockFormatException.class, () -> {
            socksService.outcomeSocks(new SockDto("green", 46, 324));
        });
    }

    @Test
    public void getSocksWhereCottonPartGreaterThanTest() {
        SockDto sockDto = new SockDto(
                "green",
                40,
                sockList.get(0).getQuantity() + sockList.get(1).getQuantity());

        Mockito.when(socksRepositoryMock.findByColorAndCottonPartGreaterThan("green", 40))
                .thenReturn(List.of(sockList.get(0), sockList.get(1)));
        Assertions.assertEquals(sockDto, socksService.getSocksByParams("green", "moreThan", 40));
    }

    @Test
    public void getSocksWhereCottonPartLessThanTest() {
        SockDto sockDto = new SockDto(
                "red",
                90,
                sockList.get(2).getQuantity());

        Mockito.when(socksRepositoryMock.findByColorAndCottonPartLessThan("red", 90))
                .thenReturn(List.of(sockList.get(2)));
        Assertions.assertEquals(sockDto, socksService.getSocksByParams("red", "lessThan", 90));
    }

    @Test
    public void getSocksWhereCottonPartEqualTest() {
        SockDto sockDto = new SockDto(
                "red",
                sockList.get(2).getCottonPart(),
                sockList.get(2).getQuantity());

        Mockito.when(socksRepositoryMock.findByColorAndCottonPartEquals(sockList.get(2).getColor(),
                sockList.get(2).getCottonPart()))
                .thenReturn(List.of(sockList.get(2)));

        Assertions.assertEquals(sockDto, socksService.getSocksByParams(sockList.get(2).getColor(), "equal",
                sockList.get(2).getCottonPart()));
    }

    @Test
    public void getSockByParamsWithIncorrectParamTestShouldReturnIncorrectSockFormatException() {
        Assertions.assertThrows(IncorrectSockFormatException.class, () -> {
            socksService.getSocksByParams("red", "FAKE", 32);
        });
    }

    @Test
    public void getSockByParamsWithIncorrectParamTestShouldReturnNotFoundSockException() {
        Mockito.when(socksRepositoryMock.findByColorAndCottonPartLessThan("red", 32))
                .thenReturn(Collections.emptyList());

        Assertions.assertThrows(NotFoundSockException.class, () -> {
            socksService.getSocksByParams("red", "lessThan", 32);
        });
    }

}
