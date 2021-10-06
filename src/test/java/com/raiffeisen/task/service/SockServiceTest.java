package com.raiffeisen.task.service;

import com.raiffeisen.task.domain.Sock;
import com.raiffeisen.task.exception.ValidationException;
import com.raiffeisen.task.repository.SockRepository;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest
class SockServiceTest {

    public static final String COLOR = "red";
    public static final int COTTON_PART = 99;
    public static final int QUANTITY = 1;
    public static final String INVALID_OPERATION = "invalid operation";

    @Autowired
    private SockService service;

    @MockBean
    private SockRepository repository;

    @Test
    void checkAddSocksFindSock(){
        when(repository.findSockByColorAndCottonPart(COLOR, COTTON_PART))
                .thenReturn(Optional.of(new Sock( 1, COLOR, COTTON_PART, 9)));
        service.addSocks(COLOR, COTTON_PART, 11);
        verify(repository).save(new Sock(1, COLOR, COTTON_PART, 20));
    }

    @Test
    void checkAddSocksNotFoundSock(){
        when(repository.findSockByColorAndCottonPart(COLOR, COTTON_PART))
                .thenReturn(Optional.empty());
        service.addSocks(COLOR, COTTON_PART, 11);
        verify(repository).save(new Sock(null, COLOR, COTTON_PART, 11));
    }

    @Test
    void checkRemoveSocksSuccessful() {
        when(repository.findSockByColorAndCottonPart(COLOR, COTTON_PART))
                .thenReturn(Optional.of(new Sock(1, COLOR, COTTON_PART, 9)));
        service.removeSocks(COLOR, COTTON_PART, 8);
        verify(repository, times(1))
                .save(new Sock(1,COLOR, COTTON_PART, 1));
    }

    @Test
    void checkRemoveSocksNotFound() {
        when(repository.findSockByColorAndCottonPart(COLOR, COTTON_PART))
                .thenReturn(Optional.empty());

        assertThrows(ValidationException.class, () -> {
            service.removeSocks(COLOR, COTTON_PART, QUANTITY);
        });
        verify(repository, times(0))
                .save(new Sock(1,COLOR, COTTON_PART, QUANTITY));
    }

    @Test
    void checkRemoveSocksMoreThanProblem() {
        when(repository.findSockByColorAndCottonPart(COLOR, COTTON_PART))
                .thenReturn(Optional.of(new Sock(1, COLOR, COTTON_PART, 9)));

        assertThrows(ValidationException.class, () -> {
            service.removeSocks(COLOR, COTTON_PART, 10);
        });

        verify(repository, times(0))
                .save(new Sock(1,COLOR, COTTON_PART, QUANTITY));
    }

    @Test
    void checkGetTotalSocksByParamInvalidOperator() {
        assertThrows(ValidationException.class, () -> {
            service.getTotalSocksByParam(COLOR, COTTON_PART, INVALID_OPERATION);
        });
        verify(repository, times(0))
                .save(new Sock(1,COLOR, COTTON_PART, QUANTITY));
    }


}