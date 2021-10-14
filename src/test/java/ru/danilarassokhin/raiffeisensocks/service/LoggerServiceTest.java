package ru.danilarassokhin.raiffeisensocks.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import ru.danilarassokhin.raiffeisensocks.EmbeddedTest;
import ru.danilarassokhin.raiffeisensocks.service.impl.LoggerService;

@SpringBootTest
public class LoggerServiceTest extends EmbeddedTest {

    @Test
    public void singletonTest() {
        Assertions.assertEquals(LoggerService.getInstance(), LoggerService.getInstance());
        Assertions.assertEquals("LoggerService", LoggerService.getInstance().getLogger().getName());
    }

    @Test
    public void allLoggersDontThrowTest() {
        Assertions.assertDoesNotThrow(() -> LoggerService.info(""));
        Assertions.assertDoesNotThrow(() -> LoggerService.warn(""));
        Assertions.assertDoesNotThrow(() -> LoggerService.error(""));
    }

}
