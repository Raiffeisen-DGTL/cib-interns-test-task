package ru.danilarassokhin.raiffeisensocks.mapper;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import ru.danilarassokhin.raiffeisensocks.EmbeddedTest;
import ru.danilarassokhin.raiffeisensocks.dto.SocksIncomeDto;
import ru.danilarassokhin.raiffeisensocks.model.Socks;

@SpringBootTest
public class SocksMapperTest extends EmbeddedTest {

    @Test
    public void socksIncomeDtoToSocks() {
        SocksIncomeDto socksIncomeDto = new SocksIncomeDto();
        socksIncomeDto.setCottonPart((byte) 10);
        socksIncomeDto.setColor("red");
        socksIncomeDto.setQuantity(1L);

        Socks model = SocksMapper.INSTANCE.incomeDtoToSocks(socksIncomeDto);

        Assertions.assertEquals(socksIncomeDto.getColor(), model.getColor());
        Assertions.assertEquals(socksIncomeDto.getCottonPart(), model.getCottonPart());
        Assertions.assertEquals(socksIncomeDto.getQuantity(), model.getQuantity());
    }

}
