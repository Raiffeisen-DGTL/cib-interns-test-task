package ru.dnsk.accountingofsocks.service;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.web.server.ResponseStatusException;
import ru.dnsk.accountingofsocks.model.PairOfSocks;
import ru.dnsk.accountingofsocks.repository.PairOfSocksRepository;

@RunWith(MockitoJUnitRunner.class)
public class PairOfSocksServiceTest {

    @InjectMocks
    private PairOfSocksService pairOfSocksService;

    @Mock
    private PairOfSocksRepository pairOfSocksRepository;

    @Test
    public void changeQuantity() {
        PairOfSocks pairOfSocksTest = createTestObjectAndRepositorySubbing();

        pairOfSocksService.changeQuantity("white", 100, 50);

        Assert.assertEquals(850, pairOfSocksTest.getQuantity());
        Mockito.verify(pairOfSocksRepository, Mockito.times(1)).save(pairOfSocksTest);
    }


    @Test
    public void changeQuantityToZero() {
        PairOfSocks pairOfSocksTest = createTestObjectAndRepositorySubbing();

        PairOfSocks changingPairOfSocks = pairOfSocksService.changeQuantity("white", 100, -800);

        Assert.assertNull(changingPairOfSocks);
        Mockito.verify(pairOfSocksRepository, Mockito.times(1)).delete(pairOfSocksTest);
    }

    @Test
    public void changeQuantityMuch() {
        createTestObjectAndRepositorySubbing();

        Assert.assertThrows(ResponseStatusException.class,
                () -> pairOfSocksService.changeQuantity("white", 100, -1000));
    }

    @Test
    public void getQuantityByColorAndCottonPart() {
        createTestObjectAndRepositorySubbing();

        String quantity = pairOfSocksService.getQuantityByColorAndCottonPart("white", "equal", 100);

        Assert.assertEquals("800", quantity);
        Mockito.verify(pairOfSocksRepository, Mockito.times(1))
                .findByColorAndCottonPart(ArgumentMatchers.eq("white"), ArgumentMatchers.eq(100));
    }

    @Test
    public void getQuantityIncorrectOperation() {
        Assert.assertThrows(ResponseStatusException.class,
                () -> pairOfSocksService.getQuantityByColorAndCottonPart("white", "incorrect", 100));
    }

    private PairOfSocks createTestObjectAndRepositorySubbing() {
        PairOfSocks pairOfSocksTest = new PairOfSocks("white", 100, 800);

        Mockito.doReturn(pairOfSocksTest)
                .when(pairOfSocksRepository)
                .findByColorAndCottonPart("white", 100);
        return pairOfSocksTest;
    }
}