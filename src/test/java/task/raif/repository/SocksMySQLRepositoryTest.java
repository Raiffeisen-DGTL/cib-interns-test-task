package task.raif.repository;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;
import task.raif.model.SocksStorage;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@RunWith(SpringRunner.class)
@DataJpaTest
class SocksMySQLRepositoryTest {

    @Autowired
    private SocksMySQLRepository repository;

    @Autowired
    private TestEntityManager entityManager;


    private List<SocksStorage> getSocksStorage() {
        return List.of(new SocksStorage("red", 10, 10),
                       new SocksStorage("red", 50, 10),
                       new SocksStorage("blue", 50, 15));
    }

    @Test
    void getByColorAndCottonPart() {
        getSocksStorage().stream().forEach(s -> {entityManager.persist(s);});
        repository.findAll().stream().forEach(s ->
                                                      assertEquals(s, repository
                                                              .getByColorAndCottonPart(s.getColor(), s.getCottonPart())
                                                              .get())
        );
        assertEquals(Optional.empty(), repository.getByColorAndCottonPart("black", 10));
    }

    @Test
    void getQuantityByColorAndCottonPartLessThan() {
        getSocksStorage().stream().forEach(s -> entityManager.persist(s));
        assertEquals(Optional.empty(), repository.getQuantityByColorAndCottonPartLessThan("black", 10));
        assertEquals(Optional.of(20), repository.getQuantityByColorAndCottonPartLessThan("red", 100));
        assertEquals(Optional.of(10), repository.getQuantityByColorAndCottonPartLessThan("red", 50));
        assertEquals(Optional.empty(), repository.getQuantityByColorAndCottonPartLessThan("red", 10));
        assertEquals(Optional.empty(), repository.getQuantityByColorAndCottonPartLessThan(null, 0));
    }

    @Test
    void getQuantityByColorAndCottonPartMoreThan() {
        getSocksStorage().stream().forEach(s -> entityManager.persist(s));
        assertEquals(Optional.empty(), repository.getQuantityByColorAndCottonPartMoreThan("black", 10));
        assertEquals(Optional.empty(), repository.getQuantityByColorAndCottonPartMoreThan("red", 100));
        assertEquals(Optional.empty(), repository.getQuantityByColorAndCottonPartMoreThan("red", 50));
        assertEquals(Optional.of(10), repository.getQuantityByColorAndCottonPartMoreThan("red", 10));
        assertEquals(Optional.of(20), repository.getQuantityByColorAndCottonPartMoreThan("red", 0));
        assertEquals(Optional.empty(), repository.getQuantityByColorAndCottonPartMoreThan(null, 0));
    }
}