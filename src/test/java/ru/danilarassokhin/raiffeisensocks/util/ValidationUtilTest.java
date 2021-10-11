package ru.danilarassokhin.raiffeisensocks.util;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import ru.danilarassokhin.raiffeisensocks.EmbeddedTest;
import ru.danilarassokhin.raiffeisensocks.dto.SocksIncomeDto;

@SpringBootTest
public class ValidationUtilTest extends EmbeddedTest {

    @Test
    public void testValidationUtilConstraintInvalidTest() {
        SocksIncomeDto socksIncomeDto = new SocksIncomeDto();
        socksIncomeDto.setQuantity(-1L);
        socksIncomeDto.setColor("red");
        socksIncomeDto.setCottonPart((byte) -1);

        Assertions.assertFalse(ValidationUtils.isValid(socksIncomeDto));
    }

    @Test
    public void testValidationUtlConstraintValid() {
        SocksIncomeDto socksIncomeDto = new SocksIncomeDto();
        socksIncomeDto.setQuantity(1L);
        socksIncomeDto.setColor("red");
        socksIncomeDto.setCottonPart((byte) 1);

        Assertions.assertTrue(ValidationUtils.isValid(socksIncomeDto));
    }

}
