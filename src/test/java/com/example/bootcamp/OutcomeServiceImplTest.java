package com.example.bootcamp;

import com.example.bootcamp.dto.SocksDto;
import com.example.bootcamp.entity.SocksEntity;
import com.example.bootcamp.repo.SocksRepo;
import com.example.bootcamp.service.OutcomeServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class OutcomeServiceImplTest {

    static final int QUANTITY = 50;
    static final int CNT_LESS_THAN_EXISTENT = 6;
    static final int CNT_BIGGER_THAN_EXISTENT = 88;
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

    OutcomeServiceImpl outcomeService;

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

        when(socksRepo.fingByColorAndCotton(NON_EXISTENT, COTTON_EXISTENT))
                .thenReturn(
                        Optional.empty()
                );

        outcomeService = new OutcomeServiceImpl(socksRepo);
    }

    @Test
    void проверитьСлучайКогдаБеретсяКоличествоТовараМеньшеОстатка() {

        outcomeService.outcome(List.of(new SocksDto(EXISTENT, COTTON_EXISTENT, CNT_LESS_THAN_EXISTENT)));

        ArgumentCaptor<SocksEntity> captor = ArgumentCaptor.forClass(SocksEntity.class);
        verify(socksRepo).save(captor.capture());

        assertEquals(QUANTITY - CNT_LESS_THAN_EXISTENT, captor.getValue().getQuantityEntity());
    }

    @Test
    void проверитьСлучайКогдаТоБеретсяКоличествоТовараБольшеОстатка() {

        Assertions.assertThrows(
                RuntimeException.class,
                () -> outcomeService.outcome(List.of(new SocksDto(EXISTENT, COTTON_EXISTENT,
                        CNT_BIGGER_THAN_EXISTENT)))
        );
    }

    @Test
    void проверитьСлучайКогдаБеретсяКоличествоТовараРавноеОстатку() {

        outcomeService.outcome(List.of(new SocksDto(EXISTENT, COTTON_EXISTENT, QUANTITY)));
        verify(socksRepo).delete(any());

        verify(socksRepo, times(0)).save(any());
    }

    @Test
    void проверитьСлучайКогдаЗадаетсяНесуществующееПоле() {
        Assertions.assertThrows(
                RuntimeException.class,
                () -> outcomeService.outcome(List.of(new SocksDto(NON_EXISTENT, COTTON_EXISTENT, CNT_LESS_THAN_EXISTENT)))
        );
    }

    @Test
    void проверитьСлучайКогдаПоступаетПустойЗапрос() {
        outcomeService.outcome(List.of());

        verifyNoInteractions(outcomeService);
    }

    @Test
    void shouldThrowExceptionWhenArgContainsNegativeQuantity() {
        Assertions.assertThrows(
                RuntimeException.class,
                () -> outcomeService.outcome(List.of(new SocksDto(EXISTENT, COTTON_EXISTENT, -5))
                ));
    }

    @Test
    void shouldThrowExceptionWhenArgContainsNegativeCotton() {
        Assertions.assertThrows(
                RuntimeException.class,
                () -> outcomeService.outcome(List.of(new SocksDto(EXISTENT, -8, CNT_LESS_THAN_EXISTENT))
                ));
    }

    @Test
    void shouldThrowExceptionWhenArgContainsCottonMore100() {
        Assertions.assertThrows(
                RuntimeException.class,
                () -> outcomeService.outcome(List.of(new SocksDto(EXISTENT, 567, CNT_LESS_THAN_EXISTENT))
                ));
    }
}
