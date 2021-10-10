package ru.danilarassokhin.raiffeisensocks.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.danilarassokhin.raiffeisensocks.EmbeddedTest;
import ru.danilarassokhin.raiffeisensocks.dto.SocksIncomeDto;
import ru.danilarassokhin.raiffeisensocks.dto.SocksOutcomeDto;
import ru.danilarassokhin.raiffeisensocks.dto.SocksSearchDto;
import ru.danilarassokhin.raiffeisensocks.exception.DataValidityException;

@SpringBootTest
public class SocksServiceTest extends EmbeddedTest {

    @Autowired
    private SocksService socksService;

    @Test
    public void testInvalidIncome() {
        SocksIncomeDto socksIncomeDto = new SocksIncomeDto();
        socksIncomeDto.setCottonPart((byte) -1);
        socksIncomeDto.setColor("redd");
        socksIncomeDto.setQuantity(-1L);

        Assertions.assertThrows(DataValidityException.class, () -> socksService.income(socksIncomeDto));
    }

    @Test
    public void testInvalidOutcome() {
        SocksOutcomeDto socksOutcomeDto = new SocksOutcomeDto();
        socksOutcomeDto.setCottonPart((byte) -1);
        socksOutcomeDto.setColor("redd");
        socksOutcomeDto.setQuantity(-1L);

        Assertions.assertThrows(DataValidityException.class, () -> socksService.outcome(socksOutcomeDto));
    }

    @Test
    public void testInvalidOperationInSocksCount() {
        SocksSearchDto socksSearchDto = new SocksSearchDto();
        socksSearchDto.setOperation("equals");
        socksSearchDto.setColor("red");
        socksSearchDto.setCottonPart((byte) 10);

        Assertions.assertThrows(DataValidityException.class, () -> socksService.countSocks(socksSearchDto));
    }
}
