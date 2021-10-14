package com.example.company;

import com.example.company.dao.SockRepository;
import com.example.company.entity.Operation;
import com.example.company.entity.Sock;
import com.example.company.exception.NullResultException;
import com.example.company.service.SockService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Objects;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
class RaiffeisenInternshipApplicationTests {

    @Autowired
    private SockService service;

    @Test
    void getByColorAndCottonPart() {
        Sock sock = new Sock();
        sock.setQuantity(12);
        sock.setColor("green");
        sock.setCottonPart(28);

        service.addSocks(sock);
        Sock getFromDB = service.getSockByColorAndCottonPart(sock.getColor(), Operation.equals, sock.getCottonPart());
        assertThat(sock.getColor()).isEqualTo(getFromDB.getColor());
        assertThat(sock.getCottonPart()).isEqualTo(getFromDB.getCottonPart());
        assertThat(sock.getQuantity()).isEqualTo(getFromDB.getQuantity());

        assertThrows (NullResultException.class,()-> service.getSockByColorAndCottonPart("white",
                Operation.moreThan,
                85));
        assertThrows (NullResultException.class,()-> service.getSockByColorAndCottonPart("white",
                Operation.lessThan,
                15));


    }

}
