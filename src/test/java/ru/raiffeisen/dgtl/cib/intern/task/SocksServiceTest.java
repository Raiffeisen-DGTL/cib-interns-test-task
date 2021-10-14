package ru.raiffeisen.dgtl.cib.intern.task;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import ru.raiffeisen.dgtl.cib.intern.task.entity.Socks;
import ru.raiffeisen.dgtl.cib.intern.task.service.SocksServiceImpl;

import java.util.List;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;


@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class SocksServiceTest {

    @Mock
    private SocksRepository socksRepository;

    @InjectMocks
    private SocksServiceImpl socksService;

    private static List<Socks> socksList;

    @BeforeAll
    static void init() {
        socksList = List.of(
                new Socks("black", (byte) 70, 50L),
                new Socks("green", (byte) 10, 100L),
                new Socks("red", (byte) 90, 58L)
        );
    }

    @Test
    void incomeSocks() {
        socksService.income(socksList.get(0));
        verify(socksRepository, times(1)).save(socksList.get(0));
    }
}
