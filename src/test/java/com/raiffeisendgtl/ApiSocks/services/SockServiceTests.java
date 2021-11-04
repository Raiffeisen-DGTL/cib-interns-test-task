package com.raiffeisendgtl.ApiSocks.services;

import com.raiffeisendgtl.ApiSocks.components.*;
import com.raiffeisendgtl.ApiSocks.entities.Socks;
import com.raiffeisendgtl.ApiSocks.repositories.SocksRepository;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.*;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class SockServiceTests {

    private SocksService socksService;

    @Mock
    private SocksRepository socksRepository;

    @Mock
    private Socks socks;

    @BeforeEach
    public void setUp() {
        socksRepository = mock(SocksRepository.class);
        socksService = new SocksServiceImpl(socksRepository);
    }

    @Test
    public void incomeNewSocks() {
        socks = mock(Socks.class);

        when(socksRepository.findByColorAndCottonPart(socks.getColor(), socks.getCottonPart()))
                .thenReturn(Optional.empty());

        socksService.income(socks);

        verify(socksRepository, times(1))
                .findByColorAndCottonPart(socks.getColor(), socks.getCottonPart());
        verify(socks, never()).addQuantity(socks.getQuantity());
        verify(socksRepository, times(1)).save(socks);
    }

    @Test
    public void incomeExistingSocks() {
        socks = mock(Socks.class);

        when(socksRepository.findByColorAndCottonPart(socks.getColor(), socks.getCottonPart()))
                .thenReturn(Optional.of(socks));

        socksService.income(socks);

        verify(socksRepository, times(1))
                .findByColorAndCottonPart(socks.getColor(), socks.getCottonPart());
        verify(socks, times(1)).addQuantity(socks.getQuantity());
        verify(socksRepository, times(1)).save(socks);
    }

    @Test
    public void incomeIfFindThrowError() {
        socks = mock(Socks.class);
        SocksErrorCode resultErrorCode = SocksErrorCode.SERVER_CRASH;

        when(socksRepository.findByColorAndCottonPart(socks.getColor(), socks.getCottonPart()))
                .thenThrow(new SocksException(SocksErrorCode.SERVER_CRASH));

        try {
            socksService.income(socks);
            fail();
        } catch (SocksException e) {
            assertEquals(resultErrorCode, e.getError());
        }

        verify(socksRepository, times(1))
                .findByColorAndCottonPart(socks.getColor(), socks.getCottonPart());
        verify(socks, never()).addQuantity(socks.getQuantity());
        verify(socksRepository, never()).save(socks);
    }

    @Test
    public void incomeIfSaveThrowError() {
        socks = mock(Socks.class);
        SocksErrorCode resultErrorCode = SocksErrorCode.SERVER_CRASH;

        when(socksRepository.findByColorAndCottonPart(socks.getColor(), socks.getCottonPart()))
                .thenReturn(Optional.empty());
        doThrow(new SocksException(SocksErrorCode.SERVER_CRASH)).when(socksRepository)
                .save(socks);

        try {
            socksService.income(socks);
            fail();
        } catch (SocksException e) {
            assertEquals(resultErrorCode, e.getError());
        }

        verify(socksRepository, times(1))
                .findByColorAndCottonPart(socks.getColor(), socks.getCottonPart());
        verify(socks, never()).addQuantity(socks.getQuantity());
        verify(socksRepository, times(1)).save(socks);
    }

    @Test
    public void outcomeExistingSocks() {
        socks = mock(Socks.class);

        when(socksRepository.findByColorAndCottonPart(socks.getColor(), socks.getCottonPart()))
                .thenReturn(Optional.of(socks));

        socksService.outcome(socks);

        verify(socksRepository, times(1))
                .findByColorAndCottonPart(socks.getColor(), socks.getCottonPart());
        verify(socks, times(1)).subtractQuantity(socks.getQuantity());
        verify(socksRepository, times(1)).save(socks);
    }

    @Test
    public void outcomeIfNotFoundSocks() {
        socks = mock(Socks.class);
        SocksErrorCode expectedResult = SocksErrorCode.INCORRECT_PARAMS;

        when(socksRepository.findByColorAndCottonPart(socks.getColor(), socks.getCottonPart()))
                .thenReturn(Optional.empty());

        SocksException exception = assertThrows(SocksException.class, () -> {
            socksService.outcome(socks);
        });

        assertEquals(expectedResult, exception.getError());

        verify(socksRepository, times(1))
                .findByColorAndCottonPart(socks.getColor(), socks.getCottonPart());
        verify(socks, never()).subtractQuantity(socks.getQuantity());
        verify(socksRepository, never()).save(socks);
    }

    @Test
    public void getCountSocksIfOperationIsLessThan() {
        String color = "green";
        String operation = "lessThan";
        int cottonPart = 33;
        Integer expectedCount = 3;

        when(socksRepository.findCountSocksLessThan(color, cottonPart)).thenReturn(expectedCount);

        Integer actualCount = socksService.getCountSocks(color, operation, cottonPart);

        assertEquals(expectedCount, actualCount);

        verify(socksRepository, times(1))
                .findCountSocksLessThan(color, cottonPart);
        verify(socksRepository, never())
                .findCountSocksEqual(color, cottonPart);
        verify(socksRepository, never())
                .findCountSocksMoreThan(color, cottonPart);
    }

    @Test
    public void getCountSocksIfOperationIsEqual() {
        String color = "yellow";
        String operation = "equal";
        int cottonPart = 70;
        Integer expectedCount = 5;

        when(socksRepository.findCountSocksEqual(color, cottonPart)).thenReturn(expectedCount);

        Integer actualCount = socksService.getCountSocks(color, operation, cottonPart);

        assertEquals(expectedCount, actualCount);

        verify(socksRepository, never())
                .findCountSocksLessThan(color, cottonPart);
        verify(socksRepository, times(1))
                .findCountSocksEqual(color, cottonPart);
        verify(socksRepository, never())
                .findCountSocksMoreThan(color, cottonPart);
    }

    @Test
    public void getCountSocksIfOperationIsMoreThan() {
        String color = "blue";
        String operation = "moreThan";
        int cottonPart = 80;
        Integer expectedCount = 7;

        when(socksRepository.findCountSocksMoreThan(color, cottonPart)).thenReturn(expectedCount);

        Integer actualCount = socksService.getCountSocks(color, operation, cottonPart);

        assertEquals(expectedCount, actualCount);

        verify(socksRepository, never())
                .findCountSocksLessThan(color, cottonPart);
        verify(socksRepository, never())
                .findCountSocksEqual(color, cottonPart);
        verify(socksRepository, times(1))
                .findCountSocksMoreThan(color, cottonPart);
    }

    @Test
    public void getCountSocksIfThrowExceptionIsServerCrash() {
        String color = "black";
        String operation = "equal";
        int cottonPart = 50;
        SocksErrorCode resultErrorCode = SocksErrorCode.SERVER_CRASH;

        doThrow(new SocksException(SocksErrorCode.SERVER_CRASH)).when(socksRepository)
                .findCountSocksEqual(color, cottonPart);

        try {
            socksService.getCountSocks(color, operation, cottonPart);
        } catch (SocksException e) {
            assertEquals(resultErrorCode, e.getError());
        }

        verify(socksRepository, never())
                .findCountSocksLessThan(color, cottonPart);
        verify(socksRepository, times(1))
                .findCountSocksEqual(color, cottonPart);
        verify(socksRepository, never())
                .findCountSocksMoreThan(color, cottonPart);
    }

    @Test
    public void getCountSocksIfThrowExceptionIsIncorrectParams() {
        String color = "orange";
        String operation = "equal";
        int cottonPart = 95;
        SocksErrorCode resultErrorCode = SocksErrorCode.INCORRECT_PARAMS;

        when(socksRepository.findCountSocksEqual(color, cottonPart))
                        .thenReturn(null);

        SocksException exception = assertThrows(SocksException.class, () -> {
            socksService.getCountSocks(color, operation, cottonPart);
        });

        assertEquals(resultErrorCode, exception.getError());

        verify(socksRepository, never())
                .findCountSocksLessThan(color, cottonPart);
        verify(socksRepository, times(1))
                .findCountSocksEqual(color, cottonPart);
        verify(socksRepository, never())
                .findCountSocksMoreThan(color, cottonPart);
    }

}
