package com.example.socksspring.controller;

import com.example.socksspring.Compare;
import com.example.socksspring.Socks;
import com.example.socksspring.repositories.SocksRepository;
import com.example.socksspring.service.SocksService;
import io.swagger.v3.oas.annotations.Operation;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.stereotype.Repository;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;

@SpringBootTest
public class SocksServiceTest {
    @InjectMocks
    @Autowired
    private SocksService socksService;

    @MockBean
    private SocksRepository socksRepository;

    @Captor
    private ArgumentCaptor<Socks> socksCaptor;

    @Test
    void countSocksGreater() {
        when(socksRepository.getSocksGreaterThan("red", 50))
                .thenReturn(java.util.Arrays.asList(new Socks(null, "red", 51, 5)));

        Integer count = socksService.getAmountOfSocks("red", Compare.MoreThan, 50);
        Assertions.assertEquals(count, 5);
    }

    @Test
    void countSocksEqual() {
        when(socksRepository.getSocksEquals("red", 50))
                .thenReturn(java.util.Arrays.asList(new Socks(null, "red", 51, 5)));

        Integer count = socksService.getAmountOfSocks("red", Compare.Equals, 50);
        Assertions.assertEquals(count, 5);
    }

    @Test
    void countSocksLess() {
        when(socksRepository.getSocksLessThan("red", 50))
                .thenReturn(java.util.Arrays.asList(new Socks(null, "red", 51, 5)));

        Integer count = socksService.getAmountOfSocks("red", Compare.LessThan, 50);
        Assertions.assertEquals(count, 5);
    }

    
    @Operation(summary = "Проверка на отпуск носков")
    @Test
    public void removeSocksValid() throws Exception {
        Mockito.when(socksRepository.getSocksEquals("red", 50))
                .thenReturn(Arrays.asList(new Socks(null, "red", 50, 5)));

        Socks socksToRemove = new Socks(null, "red", 50, 4);
        socksService.removeSocks(socksToRemove);

        Mockito.verify(socksRepository, times(1)).updateRemoveSocks(socksCaptor.capture());

        Assertions.assertEquals(socksCaptor.getValue().getColor(), "red");
        Assertions.assertEquals(socksCaptor.getValue().getCottonPart(), 50);
        Assertions.assertEquals(socksCaptor.getValue().getQuantity(), 4);
    }

}
