package com.task.raif;
import com.task.raif.dto.SocksDto;
import com.task.raif.service.SocksServiceImpl;
import org.junit.Before;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class ServiceTest {
    @Autowired
    SocksServiceImpl socksService;

    List<SocksDto> socksDtoList;

    @Before
    public void setUp() {
        socksDtoList = List.of(
                new SocksDto("red", 100, 1),
                new SocksDto("black", 50, 2)
        );
    }

    @Test
    public void testIncome() {
        int quantity = socksService.getSocksByParams("red", "equal", 100);
        socksService.income(new SocksDto("red", 100, 1));
        int new_quantity = socksService.getSocksByParams("red", "equal", 100);
        Assertions.assertEquals(quantity + 1, new_quantity);
        socksService.income(new SocksDto("red", 100, 1));
        new_quantity = socksService.getSocksByParams("red", "equal", 100);
        Assertions.assertEquals(quantity + 2, new_quantity);
    }

    @Test
    public void testOutcome() {
        int quantity = socksService.getSocksByParams("red", "equal", 100);
        socksService.income(new SocksDto("red", 100, 1));
        int new_quantity = socksService.getSocksByParams("red", "equal", 100);
        Assertions.assertEquals(quantity + 1, new_quantity);
        socksService.income(new SocksDto("red", 100, 1));
        new_quantity = socksService.getSocksByParams("red", "equal", 100);
        Assertions.assertEquals(quantity + 2, new_quantity);
        socksService.outcome(new SocksDto("red", 100, 1));
        new_quantity = socksService.getSocksByParams("red", "equal", 100);
        Assertions.assertEquals(quantity + 1, new_quantity);
    }
}
