package ru.danilarassokhin.raiffeisensocks.util;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import ru.danilarassokhin.raiffeisensocks.EmbeddedTest;
import ru.danilarassokhin.raiffeisensocks.dto.SocksIncomeDto;

public class ValidationUtilTest {

  @Test
  public void testValidationUtilConstraintInvalidTest() {
    SocksIncomeDto socksIncomeDto = new SocksIncomeDto("red", (byte) -1, -1L);

    Assertions.assertFalse(ValidationUtils.isValid(socksIncomeDto));
  }

  @Test
  public void testValidationUtlConstraintValid() {
    SocksIncomeDto socksIncomeDto = new SocksIncomeDto("red", (byte) 1, 1L);

    Assertions.assertTrue(ValidationUtils.isValid(socksIncomeDto));
  }

}
