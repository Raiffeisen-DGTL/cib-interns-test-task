package ru.danilarassokhin.raiffeisensocks.repository;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.TransactionSystemException;
import ru.danilarassokhin.raiffeisensocks.EmbeddedTest;
import ru.danilarassokhin.raiffeisensocks.model.Socks;
import ru.danilarassokhin.raiffeisensocks.model.SocksId;

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

    @Test
    public void testValidSocks() {
        Socks test = new Socks();
        test.setColor("red");
        test.setCottonPart((byte) 1);
        test.setQuantity(2L);

        Assertions.assertDoesNotThrow(() -> socksRepository.save(test));

        SocksId socksId = new SocksId();
        socksId.setColor("red");
        socksId.setCottonPart((byte) 1);

        Assertions.assertEquals(test, socksRepository.findById(socksId).orElse(null));
    }

    @Test
    public void testSocksEquality() {
        Socks s1 = new Socks();
        s1.setQuantity(1L);
        s1.setCottonPart((byte) 10);
        s1.setColor("yellow");

        Socks s2 = new Socks();
        s2.setQuantity(2L);
        s2.setCottonPart((byte) 10);
        s2.setColor("yellow");

        SocksId s1Id = s1.getId();
        SocksId s2Id = s2.getId();

        Assertions.assertEquals(s1Id, s2Id);
        Assertions.assertTrue(s1Id.equals(s2Id));

        Assertions.assertEquals(s1, s2);
        Assertions.assertTrue(s1.equals(s2));

    }

    @Test
    public void testSocksHashcodeEquality() {
        Socks s1 = new Socks();
        s1.setQuantity(1L);
        s1.setCottonPart((byte) 10);
        s1.setColor("yellow");

        Socks s2 = new Socks();
        s2.setQuantity(2L);
        s2.setCottonPart((byte) 10);
        s2.setColor("yellow");

        SocksId s1Id = s1.getId();
        SocksId s2Id = s2.getId();

        Assertions.assertEquals(s1Id.hashCode(), s2Id.hashCode());

        Assertions.assertEquals(s1.hashCode(), s2.hashCode());
    }

}
