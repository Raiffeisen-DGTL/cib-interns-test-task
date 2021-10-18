package ru.raiffeisen.socksforraif.services;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class SocksServiceTest {

    private final String operation = "moreThan";


    @Test
    void isCorrectOperation() {
//        when(Arrays.stream(SocksServiceOperations.values()).anyMatch(ops -> ops.name().equals(operation))).thenReturn(true);
        assertTrue(Arrays.stream(SocksServiceOperations.values()).anyMatch(ops -> ops.name().equals(operation)));
    }
}