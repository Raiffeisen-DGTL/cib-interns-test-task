package org.vetirdoit.sock.registration.repositories;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.vetirdoit.sock.registration.domain.Color;
import org.vetirdoit.sock.registration.domain.entities.SockType;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@ActiveProfiles("test")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class SockRepositoryTest {

    @Autowired SockRepository sockRepository;

    @Test
    @Sql(scripts = {"/sql/createSockTypes.sql"})
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

    @Test
    @Sql(scripts = {"/sql/createSockTypes.sql"})
    void updateEntityQuery() throws Exception {
        SockType sockType = sockRepository.findById(1L).orElseThrow(Exception::new);
        assertThat(sockType.getQuantity()).isEqualTo(1);
        sockType.setQuantity(100);
        sockRepository.save(sockType);
        SockType updatedSockType = sockRepository.findById(1L).orElseThrow(Exception::new);
        assertThat(sockType.getQuantity()).isEqualTo(100);
    }

    @Test
    void insertEntityQuery() throws Exception {
        SockType sockType = SockType.createSockType(Color.BLUE, 13, 10);
        sockRepository.save(sockType);
    }

}