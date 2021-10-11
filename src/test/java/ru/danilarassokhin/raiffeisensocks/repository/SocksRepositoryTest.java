package ru.danilarassokhin.raiffeisensocks.repository;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.TransactionSystemException;
import ru.danilarassokhin.raiffeisensocks.EmbeddedTest;
import ru.danilarassokhin.raiffeisensocks.model.Socks;

@SpringBootTest
public class SocksRepositoryTest extends EmbeddedTest {

    @Autowired
    private SocksRepository socksRepository;

    private Socks s;

    @BeforeEach
    public void init() {
        socksRepository.deleteAll();

        s = new Socks();
        s.setColor("red");
        s.setCottonPart((byte) 10);
        s.setQuantity(1L);

        s = socksRepository.save(s);
    }

    @Test
    public void testConstraintsSocks() {

        Socks test = new Socks();
        test.setColor("red");
        test.setCottonPart((byte) -1);
        test.setQuantity(-2L);

        Assertions.assertThrows(TransactionSystemException.class, () -> socksRepository.save(test));
    }

}
