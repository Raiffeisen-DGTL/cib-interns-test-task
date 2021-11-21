package com.raiffeisendgtl.ApiSocks.components;

import com.raiffeisendgtl.ApiSocks.repositories.SocksRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class FinderEqualTests {

    @Mock
    private SocksRepository socksRepository;

    @BeforeEach
    public void setUp() {
        socksRepository = mock(SocksRepository.class);
    }

    @Test
    public void typeTest() {
        FinderEqual finderEqual;

        finderEqual = new FinderEqual();

        assertTrue(FinderOperation.class.isAssignableFrom(finderEqual.getClass()));
    }

    @Test
    public void findCountTest() {
        FinderEqual finderEqual = new FinderEqual();
        finderEqual.setSocksRepository(socksRepository);
        String color = "red";
        int cottonPart = 30;
        Integer expectedResult = 13;

        when(socksRepository.findCountSocksEqual(color, cottonPart)).thenReturn(expectedResult);

        Integer actualResult = finderEqual.findCount(color, cottonPart);

        assertEquals(expectedResult, actualResult);
    }

}
