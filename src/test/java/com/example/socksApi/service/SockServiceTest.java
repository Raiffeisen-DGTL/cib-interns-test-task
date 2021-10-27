package com.example.socksApi.service;

import com.example.socksApi.dao.SockRepository;
import com.example.socksApi.dto.IncomeDto;
import com.example.socksApi.dto.OutcomeDto;
import com.example.socksApi.exceptions.ApiInvalidParametersException;
import com.example.socksApi.model.Sock;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class SockServiceTest {

    @Mock
    private SockRepository sockRepository;

    @Autowired
    @InjectMocks
    private SockServiceImpl sockServiceImpl;
    
    @Test
    void income() {
        assertThrows(
                ApiInvalidParametersException.class,
                ()->sockServiceImpl.income(new IncomeDto("red", -35, 100))
        );
        assertThrows(
                ApiInvalidParametersException.class,
                ()-> sockServiceImpl.income(new IncomeDto("red", 35, -100))
        );
    }

    @Test
    void outcome() {
        when(sockRepository.findSockByColorAndCottonPart("red",25))
                .thenReturn(Optional.of(new Sock("red", 25, 50)));

        assertThrows(
                ApiInvalidParametersException.class,
                ()->sockServiceImpl.outcome(new OutcomeDto("red", 25, 75))
        );
    }

    @Test
    void count() {
        when(sockRepository.findCountLessThan(25,"red"))
                .thenReturn(Optional.of(20));
        when(sockRepository.findCountMoreThan(25,"red"))
                .thenReturn(Optional.of(35));
        when(sockRepository.findCountEqual(25,"red"))
                .thenReturn(Optional.of(13));

        assertEquals(
                sockServiceImpl.count("red","lessThan",25),
                20
        );
        assertEquals(
                sockServiceImpl.count("red","moreThan",25),
                35
        );
        assertEquals(
                sockServiceImpl.count("red","equal",25),
                13
        );
        assertThrows(
                ApiInvalidParametersException.class,
                ()->sockServiceImpl.count("red","equalThan",25)
        );

    }

}
