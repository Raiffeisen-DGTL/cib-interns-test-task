package ru.raiffeisen.socks.service.impl;

import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import ru.raiffeisen.socks.database.entity.Sock;
import ru.raiffeisen.socks.database.repository.SockRepository;
import ru.raiffeisen.socks.exception.NotEnoughSocksException;
import ru.raiffeisen.socks.exception.UnsupportedSocksOperationException;
import ru.raiffeisen.socks.service.SockService;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

@SpringBootTest
class SockServiceTest {

    @Autowired
    private SockService sockService;

    @MockBean
    private SockRepository mockRepository;

    @Test
    void whenRequestedQuantityOfSocks_thanReturnsStoredValue() {
        when(mockRepository.findQuantitySumByColorAndCottonPartEquals(any(), anyInt()))
                .thenReturn(10);

        int quantityOfSocks = sockService.getQuantityOfSocks("red", Operation.EQUALS, 50);

        assertEquals(10, quantityOfSocks);
        verify(mockRepository, times(1)).findQuantitySumByColorAndCottonPartEquals(any(), anyInt());
    }

    @Test
    void whenRequestedQuantityOfSocksForInvalidOperation_thanThrowsException() {
        assertThrows(UnsupportedSocksOperationException.class,
                () -> sockService.getQuantityOfSocks("blue", Operation.UNSUPPORTED, 15));

        verify(mockRepository, times(0)).findQuantitySumByColorAndCottonPartEquals(any(), anyInt());
    }

    @Test
    void whenRegisterNewSocks_thanSaveNewValueToRepository() {
        ArgumentCaptor<Sock> requestCaptor = ArgumentCaptor.forClass(Sock.class);
        Sock storedSock = new Sock("red", 50, 5);
        when(mockRepository.findByColorAndCottonPart(any(), anyInt()))
                .thenReturn(Optional.of(storedSock));

        sockService.registerArrivalOfSocks("red", 50, 10);

        verify(mockRepository, times(1)).save(requestCaptor.capture());
        verify(mockRepository, times(1)).findByColorAndCottonPart(any(), anyInt());
        verifyNoMoreInteractions(mockRepository);

        Sock capturedSock = requestCaptor.getValue();
        assertEquals(15, capturedSock.getQuantity());
        assertEquals("red", capturedSock.getColor());
        assertEquals(50, capturedSock.getCottonPart());
    }

    @Test
    void whenReleaseMissingSocks_thanThrowException() {
        Sock storedSock = new Sock("red", 50, 5);
        when(mockRepository.findByColorAndCottonPart(any(), anyInt()))
                .thenReturn(Optional.of(storedSock));

        assertThrows(NotEnoughSocksException.class,
                () -> sockService.registerReleaseOfSocks("red", 50, 10));

        verify(mockRepository, times(1)).findByColorAndCottonPart(any(), anyInt());
        verifyNoMoreInteractions(mockRepository);
    }

    @Test
    void whenReleaseAllSocks_thanDeleteFromRepository() {
        ArgumentCaptor<Sock> requestCaptor = ArgumentCaptor.forClass(Sock.class);
        Sock storedSock = new Sock("red", 50, 10);
        when(mockRepository.findByColorAndCottonPart(any(), anyInt()))
                .thenReturn(Optional.of(storedSock));

        sockService.registerReleaseOfSocks("red", 50, 10);

        verify(mockRepository, times(1)).delete(requestCaptor.capture());
        verify(mockRepository, times(1)).findByColorAndCottonPart(any(), anyInt());
        verifyNoMoreInteractions(mockRepository);

        Sock capturedSock = requestCaptor.getValue();
        assertEquals("red", capturedSock.getColor());
        assertEquals(50, capturedSock.getCottonPart());
    }

    @Test
    void whenReleaseSocks_thanSaveToRepository() {
        ArgumentCaptor<Sock> requestCaptor = ArgumentCaptor.forClass(Sock.class);
        Sock storedSock = new Sock("red", 50, 40);
        when(mockRepository.findByColorAndCottonPart(any(), anyInt()))
                .thenReturn(Optional.of(storedSock));

        sockService.registerReleaseOfSocks("red", 50, 10);

        verify(mockRepository, times(1)).save(requestCaptor.capture());
        verify(mockRepository, times(1)).findByColorAndCottonPart(any(), anyInt());
        verifyNoMoreInteractions(mockRepository);

        Sock capturedSock = requestCaptor.getValue();
        assertEquals(30, capturedSock.getQuantity());
        assertEquals("red", capturedSock.getColor());
        assertEquals(50, capturedSock.getCottonPart());
    }
}
