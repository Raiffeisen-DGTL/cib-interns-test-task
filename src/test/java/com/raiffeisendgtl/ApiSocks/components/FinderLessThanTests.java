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
public class FinderLessThanTests {

    @Mock
    private SocksRepository socksRepository;

    @BeforeEach
    public void setUp() {
        socksRepository = mock(SocksRepository.class);
    }

    @Test
    public void typeTest() {
        FinderLessThan finderLessThan;

        finderLessThan = new FinderLessThan();

        assertTrue(FinderOperation.class.isAssignableFrom(finderLessThan.getClass()));
    }

    @Test
    public void findCountTest() {
        FinderLessThan finderLessThan = new FinderLessThan();
        finderLessThan.setSocksRepository(socksRepository);
        String color = "black";
        int cottonPart = 25;
        Integer expectedResult = 10;

        when(socksRepository.findCountSocksLessThan(color, cottonPart)).thenReturn(expectedResult);

        Integer actualResult = finderLessThan.findCount(color, cottonPart);

        assertEquals(expectedResult, actualResult);
    }

}
