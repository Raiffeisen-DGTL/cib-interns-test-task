package ru.raiffeisen.cibinternstesttask.socks;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.server.ResponseStatusException;
import ru.raiffeisen.cibinternstesttask.socks.dto.SocksDto;

@ExtendWith(MockitoExtension.class)
class SocksServiceTest {

    private SocksService socksService;

    @Mock
    private SocksRepository socksRepository;
    @Mock
    private ColorRepository colorRepository;

    @BeforeEach
    void serviceInit() {
        socksService = new SocksService(socksRepository, colorRepository);
    }

    @Test
    void whenIncome_thenReturnNewQuantity() {
        var color = Color.of("black");
        var socks = Socks.of(color, (short)85, 120);
        when(colorRepository.save(color)).thenReturn(color);
        when(socksRepository.findSocksByColorAndCottonPart(color, (short)85))
                .thenReturn(Optional.of(socks));
        var dto = new SocksDto("black", (short)85, 40);
        var expect = Socks.of(color, (short)85, 160);
        assertThat(socksService.income(dto)).isEqualTo(expect);
    }

    @Test
    void whenOutcome_thenReturnNewQuantity() {
        var color = Color.of("yellow");
        var socks = Socks.of(color, (short)50, 80);
        when(colorRepository.findByName("yellow")).thenReturn(Optional.of(color));
        when(socksRepository.findSocksByColorAndCottonPart(color, (short)50))
                .thenReturn(Optional.of(socks));
        var dto = new SocksDto("yellow", (short)50, 70);
        var expect = Socks.of(color, (short)50, 10);
        assertThat(socksService.outcome(dto)).isEqualTo(expect);
    }

    @Test
    void whenOutcomeMoreThenExist_thenThrow() {
        var color = Color.of("yellow");
        var socks = Socks.of(color, (short)50, 80);
        when(colorRepository.findByName("yellow")).thenReturn(Optional.of(color));
        when(socksRepository.findSocksByColorAndCottonPart(color, (short)50))
                .thenReturn(Optional.of(socks));
        var dto = new SocksDto("yellow", (short)50, 90);
        assertThatExceptionOfType(ResponseStatusException.class)
                .isThrownBy(() -> socksService.outcome(dto))
                .withMessage("400 BAD_REQUEST \"Quantity must be less or equal 80\"");
    }

    @Test
    void whenOutcomeWithNotExistingColor_thenThrow() {
        when(colorRepository.findByName("yellow")).thenReturn(Optional.empty());
        var dto = new SocksDto("yellow", (short)50, 70);
        assertThatExceptionOfType(ResponseStatusException.class)
                .isThrownBy(() -> socksService.outcome(dto))
                .withMessage("400 BAD_REQUEST \"No color found with name yellow\"");
    }

    @Test
    void whenGetSocksQuantityMoreThan_thenReturnQuantity() {
        var color = Color.of("red");
        var socks1 = Socks.of(color, (short)85, 120);
        var socks2 = Socks.of(color, (short)95, 10);
        var socks3 = Socks.of(color, (short)75, 160);
        List<Socks> socksList = List.of(socks1, socks2, socks3);
        when(colorRepository.findByName("red")).thenReturn(Optional.of(color));
        when(socksRepository.findSocksByColorAndCottonPartGreaterThan(color, (short)75))
                .thenReturn(socksList);
        var actual = socksService
                .getSocksQuantity("red", "moreThan", (short)75);
        var expect = 290;
        assertThat(actual.quantity()).isEqualTo(expect);
    }

    @Test
    void whenGetSocksQuantityLessThan_thenReturnQuantity() {
        var color = Color.of("red");
        var socks1 = Socks.of(color, (short)85, 120);
        var socks2 = Socks.of(color, (short)60, 10);
        var socks3 = Socks.of(color, (short)75, 160);
        List<Socks> socksList = List.of(socks1, socks2, socks3);
        when(colorRepository.findByName("red")).thenReturn(Optional.of(color));
        when(socksRepository.findSocksByColorAndCottonPartLessThan(color, (short)75))
                .thenReturn(socksList);
        var actual = socksService
                .getSocksQuantity("red", "lessThan", (short)75);
        var expect = 290;
        assertThat(actual.quantity()).isEqualTo(expect);
    }

    @Test
    void whenGetSocksQuantityEqual_thenReturnQuantity() {
        var color = Color.of("red");
        var socks = Socks.of(color, (short)50, 80);
        when(colorRepository.findByName("red")).thenReturn(Optional.of(color));
        when(socksRepository.findSocksByColorAndCottonPart(color, (short)75))
                .thenReturn(Optional.of(socks));
        var actual = socksService
                .getSocksQuantity("red", "equal", (short)75);
        var expect = 80;
        assertThat(actual.quantity()).isEqualTo(expect);
    }

    @Test
    void whenGetSocksQuantityWithNonExistOperation_thenThrow() {
        assertThatExceptionOfType(ResponseStatusException.class)
                .isThrownBy(() -> socksService
                        .getSocksQuantity("red", "nonExistOperation", (short)75))
                .withMessage("400 BAD_REQUEST \"Unsupported operation\"");
    }

}