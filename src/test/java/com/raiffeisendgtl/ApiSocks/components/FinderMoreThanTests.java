package com.raiffeisendgtl.ApiSocks.components;

import com.raiffeisendgtl.ApiSocks.repositories.SocksRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class FinderMoreThanTests {

    @Mock
    private SocksRepository socksRepository;

    @BeforeEach
    public void setUp() {
        socksRepository = mock(SocksRepository.class);
    }

    @Test
    public void findCountTest() {
        FinderMoreThan finderMoreThan = new FinderMoreThan();
        finderMoreThan.setSocksRepository(socksRepository);
        String color = "white";
        int cottonPart = 70;
        Integer expectedResult = 7;

        when(socksRepository.findCountSocksMoreThan(color, cottonPart)).thenReturn(expectedResult);

        Integer actualResult = finderMoreThan.findCount(color, cottonPart);

        assertEquals(expectedResult, actualResult);
        assertTrue(FinderOperation.class.isAssignableFrom(finderMoreThan.getClass()));
    }

}
