package com.example.bootcamp;

import com.example.bootcamp.dto.SocksDto;
import com.example.bootcamp.entity.SocksEntity;
import com.example.bootcamp.repo.SocksRepo;
import com.example.bootcamp.service.IncomeServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class IncomeServiceImplTest {

    static final int QUANTITY = 10;
    static final int CNT = 4;
    /**
     * Цвет имеющихся в базе пар носков.
     */
    static final String EXISTENT = "Existent";

    /**
     * Цвет отсутствующих в базе пар носков.
     */

    static final String NON_EXISTENT = "Non-Existent";

    /**
     * Содержание хлопка в носках,имеющиеся в базе данных
     */
    static final int COTTON_EXISTENT = 40;

    /**
     * Содержание хлопка в носках,еще не занесенное в базу данных
     */
    static final int COTTON_NON_EXISTENT = 70;

    IncomeServiceImpl incomeService;

    SocksRepo socksRepo;


    @BeforeEach
    void setup() {
        socksRepo = mock(SocksRepo.class);

        when(socksRepo.fingByColorAndCotton(EXISTENT, COTTON_EXISTENT))
                .thenReturn(
                        Optional.of(
                                new SocksEntity(EXISTENT, COTTON_EXISTENT, QUANTITY)
                        )
                );

        when(socksRepo.fingByColorAndCotton(EXISTENT, COTTON_NON_EXISTENT))
                .thenReturn(
                        Optional.empty()
                );

        when(socksRepo.fingByColorAndCotton(NON_EXISTENT, COTTON_NON_EXISTENT))
                .thenReturn(
                        Optional.empty()
                );

        when(socksRepo.fingByColorAndCotton(NON_EXISTENT, COTTON_EXISTENT))
                .thenReturn(
                        Optional.empty()
                );

        incomeService = new IncomeServiceImpl(socksRepo);
    }

    /**
     * quantityEntity у записи увеличивается на величину переданное в параметре, если запись существует.
     */

    @Test
    void shouldIncreaseSocksQuantityWhenEntityExistent() {

        incomeService.income(List.of(new SocksDto(EXISTENT, COTTON_EXISTENT, CNT)));
        ArgumentCaptor<SocksEntity> argCaptor = ArgumentCaptor.forClass(SocksEntity.class);
        verify(socksRepo).save(argCaptor.capture());

        assertEquals(QUANTITY + CNT, argCaptor.getValue().getQuantityEntity());
    }

    @Test
    void shouldInsertNewRecordWhenColorNonExistentAndCottonExistent() {

        incomeService.income(List.of(new SocksDto(NON_EXISTENT, COTTON_EXISTENT, CNT)));
        ArgumentCaptor<SocksEntity> argCaptor = ArgumentCaptor.forClass(SocksEntity.class);
        verify(socksRepo).save(argCaptor.capture());

        assertEquals(CNT, argCaptor.getValue().getQuantityEntity());
    }

    @Test
    void shouldInsertNewRecordWhenColorNonExistentAndCottonNonExistent() {

        incomeService.income(List.of(new SocksDto(NON_EXISTENT, COTTON_NON_EXISTENT, CNT)));
        ArgumentCaptor<SocksEntity> argCaptor = ArgumentCaptor.forClass(SocksEntity.class);
        verify(socksRepo).save(argCaptor.capture());

        assertEquals(CNT, argCaptor.getValue().getQuantityEntity());
    }

    @Test
    void shouldInsertNewRecordWhenColorExistentAndCottonNonExistent() {

        incomeService.income(List.of(new SocksDto(EXISTENT, COTTON_NON_EXISTENT, CNT)));
        ArgumentCaptor<SocksEntity> argCaptor = ArgumentCaptor.forClass(SocksEntity.class);
        verify(socksRepo).save(argCaptor.capture());

        assertEquals(CNT, argCaptor.getValue().getQuantityEntity());
    }

    @Test
    void shouldNotInteractWithRepoWhenArgIsEmpty() {
        incomeService.income(List.of());

        verifyNoInteractions(socksRepo);
    }

    @Test
    void shouldThrowExceptionWhenArgContainsNegativeQuantity() {
        Assertions.assertThrows(
                RuntimeException.class,
                () -> incomeService.income(List.of(new SocksDto(EXISTENT, COTTON_EXISTENT, -5))
                ));
    }

    @Test
    void shouldThrowExceptionWhenArgContainsNegativeCotton() {
        Assertions.assertThrows(
                RuntimeException.class,
                () -> incomeService.income(List.of(new SocksDto(EXISTENT, -8, CNT))
                ));
    }

    @Test
    void shouldThrowExceptionWhenArgContainsCottonMore100() {
        Assertions.assertThrows(
                RuntimeException.class,
                () -> incomeService.income(List.of(new SocksDto(EXISTENT, 567, CNT))
                ));
    }
}
