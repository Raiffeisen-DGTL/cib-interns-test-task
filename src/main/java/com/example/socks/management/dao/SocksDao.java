package com.example.socks.management.dao;

import com.example.socks.management.dto.SocksDto;
import com.example.socks.management.dto.SocksEntity;
import org.springframework.stereotype.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

@Component
public class SocksDao {
    private static final Logger LOGGER= LoggerFactory.getLogger(SocksDao.class);
    private final Map<SocksEntity, Integer> boxSocks = new HashMap<>();


    public Integer getNumberTypeOfSocksInTheBox(SocksEntity socksEntity) {
        if (boxSocks.get(socksEntity) != null) {
            return boxSocks.get(socksEntity);
        } else {
            return 0;
        }
    }

    public void addBoxSocks(SocksEntity socksEntity, Integer quantity) {
        if (boxSocks.get(socksEntity) != null) {
            boxSocks.put(socksEntity, boxSocks.get(socksEntity) + quantity);
        } else {
            boxSocks.put(socksEntity, quantity);
        }
    }

    public void removeBoxSocks(SocksEntity socksEntity, Integer quantity) {
        if (boxSocks.get(socksEntity) == null || boxSocks.get(socksEntity) < quantity) {
            LOGGER.error("Sorry, there are not enough such socks!");
        } else if (boxSocks.get(socksEntity) > quantity) {
            boxSocks.put(socksEntity, boxSocks.get(socksEntity) - quantity);
        } else if (boxSocks.get(socksEntity) == quantity) {
            boxSocks.remove(socksEntity);
        }

    }

}

