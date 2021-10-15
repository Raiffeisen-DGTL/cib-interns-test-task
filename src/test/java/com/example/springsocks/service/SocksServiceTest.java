package com.example.springsocks.service;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;

import com.example.springsocks.domain.Socks;
import com.example.springsocks.exceptions.ValidationException;
import com.example.springsocks.repository.SocksRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

/**
 * SocksServiceTest.
 *
 * @author Alexander_Tupchin
 */
@ExtendWith({
        MockitoExtension.class
})
class SocksServiceTest {

    @Spy
    @InjectMocks
    SocksServiceImpl socksService;

    @Mock
    SocksRepository socksRepository;

    @Test
    void addEmptySocks() {
        var socks = new Socks();

        assertThrows(ValidationException.class, () -> socksService.addSocks(socks),"Socks parameters incorrect");
        verify(socksRepository, Mockito.times(0)).save(socks);
    }

    @Test
    void addSocks() {
        var socks = newSocks();
        socksService.addSocks(socks);

        verify(socksRepository, Mockito.times(1)).save(socks);
    }

    @Test
    void reduceEmptySocks() {
        var socks = new Socks();

        assertThrows(ValidationException.class, () -> socksService.reduceSocks(socks),"Socks parameters incorrect");
        verify(socksRepository, Mockito.times(0)).save(socks);
    }

    @Test
    void reduceSocks() {
        var socks = newSocks();
        socksService.reduceSocks(socks);

        verify(socksRepository, Mockito.times(0)).save(socks);
    }

    private Socks newSocks() {
        return new Socks()
                .setId(1L)
                .setColor("white")
                .setCottonPart(50)
                .setQuantity(10L);
    }

}