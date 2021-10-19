package com.example.socksservice.service;

import com.example.socksservice.exceptions.ApiInvalidParametersException;
import com.example.socksservice.model.Sock;
import com.example.socksservice.repository.SockRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class SockServiceTest {

    @Mock
    private SockRepository sockRepository;

    @Autowired
    @InjectMocks
    private SockService sockService;

    @Test
    void income() {
        assertThrows(
                ApiInvalidParametersException.class,
                ()->sockService.income(TestSockHandler.getSockWithIncorrectCottonPart())
        );
        assertThrows(
                ApiInvalidParametersException.class,
                ()-> sockService.income(TestSockHandler.getSockWithIncorrectQuantity())
        );
    }
    @Test
    void outcome() {
        when(sockRepository.findSockByColorAndCottonPart("red",12))
                .thenReturn(Optional.of(TestSockHandler.getSock()));

        assertThrows(
                ApiInvalidParametersException.class,
                ()->sockService.outcome(TestSockHandler.getSecondSockWithLargeNumberOfPairs())
        );
    }

    @Test
    void count() {
        when(sockRepository.findCountLessThan(12,"red"))
                .thenReturn(Optional.of(5));
        when(sockRepository.findCountMoreThan(12,"red"))
                .thenReturn(Optional.of(20));
        when(sockRepository.findCountEqual(12,"red"))
                .thenReturn(Optional.of(1));

        assertEquals(
                sockService.count("red","lessThan",12),
                5
                );
        assertEquals(
                sockService.count("red","moreThan",12),
                20
        );
        assertEquals(
                sockService.count("red","equal",12),
                1
        );
        assertThrows(
                ApiInvalidParametersException.class,
                ()->sockService.count("red","equalThan",12)
        );

    }
}