package org.vetirdoit.sock.registration.repositories;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.vetirdoit.sock.registration.domain.Color;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@ActiveProfiles("test")
@Sql(scripts = {"/sql/createSockTypes.sql"})
class SockRepositoryTest {

    @Autowired SockRepository sockRepository;

    @Test
    void countSockTypesWhenCottonPartHasSomeRestriction() {
        var actualValues = List.of(
                sockRepository.countSockTypesWhenCottonPartGreaterThan(Color.BLUE, -1),
                sockRepository.countSockTypesWhenCottonPartEqual(Color.BLUE, -1),
                sockRepository.countSockTypesWhenCottonPartEqual(Color.BLUE, 100),
                sockRepository.countSockTypesWhenCottonPartLessThan(Color.BLUE, 50)
        );
        var expectedValues = List.of(15L, 0L, 6L, 4L);
        assertThat(actualValues).isEqualTo(expectedValues);
    }

}