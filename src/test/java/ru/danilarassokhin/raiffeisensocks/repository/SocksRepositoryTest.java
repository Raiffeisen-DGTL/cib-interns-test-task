package ru.danilarassokhin.raiffeisensocks.repository;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataAccessException;
import org.springframework.transaction.TransactionSystemException;
import ru.danilarassokhin.raiffeisensocks.EmbeddedTest;
import ru.danilarassokhin.raiffeisensocks.model.Socks;

import javax.validation.ConstraintViolationException;

@SpringBootTest
public class SocksRepositoryTest extends EmbeddedTest {

    @Autowired
    private SocksRepository socksRepository;

    private Long id;

    @BeforeEach
    public void init() {
        socksRepository.deleteAll();

        Socks s = new Socks();
        s.setColor("red");
        s.setCottonPart((byte) 10);
        s.setQuantity(1L);

        s = socksRepository.save(s);
        id = s.getId();
    }

    @Test
    public void testConstraintsSocks() {

        Socks s = new Socks();
        s.setId(id);
        s.setColor("red");
        s.setCottonPart((byte) -1);
        s.setQuantity(-2L);

        Assertions.assertThrows(TransactionSystemException.class, () -> socksRepository.save(s));
    }

}
