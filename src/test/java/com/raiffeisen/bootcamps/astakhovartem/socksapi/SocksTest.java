package com.raiffeisen.bootcamps.astakhovartem.socksapi;

import com.raiffeisen.bootcamps.astakhovartem.socksapi.dao.SocksDAO;
import com.raiffeisen.bootcamps.astakhovartem.socksapi.entity.Socks;
import com.raiffeisen.bootcamps.astakhovartem.socksapi.service.SocksService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

@SpringBootTest
public class SocksTest {

    @Autowired
    private SocksService socksService;

    @MockBean
    private SocksDAO socksRepository;

    @Test
    void addSocks() {
        Socks socks = new Socks();
        socks.setQuantity(100);
        socks.setColor("green");
        socks.setCottonPart(90);
        socksService.increaseSocks(socks);
        Assertions.assertEquals(socks.getQuantity(), 100);
        Assertions.assertEquals(socks.getColor(), "green");
        Assertions.assertEquals(socks.getCottonPart(), 90);
        Mockito.verify(socksRepository, Mockito.times(1)).save(socks);
    }
}