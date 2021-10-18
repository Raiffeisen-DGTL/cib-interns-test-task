package com.example.socks.management.service;

import com.example.socks.management.dao.SocksDao;
import com.example.socks.management.dto.Color;
import com.example.socks.management.dto.Operation;
import com.example.socks.management.dto.SocksDto;
import com.example.socks.management.dto.SocksEntity;
import org.springframework.stereotype.Service;

@Service
public class SocksService {
    private final SocksDao socksDao;

    public SocksService(SocksDao socksDao) {
        this.socksDao = socksDao;
    }


    public void addSocks(SocksDto socksDto) {
        SocksEntity socksEntity = new SocksEntity(socksDto.getColor(), socksDto.getCottonPart());
        Integer quantity = socksDto.getQuantity();
        socksDao.addBoxSocks(socksEntity, quantity);
    }

    public void removeSocks(SocksDto socksDto) {
        SocksEntity socksEntity = new SocksEntity(socksDto.getColor(), socksDto.getCottonPart());
        Integer quantity = socksDto.getQuantity();
        socksDao.removeBoxSocks(socksEntity, quantity);
    }

    public Integer getNumberTypeOfSocks(Color color, Operation operation, Integer cottonPart) {
        Integer result = 0;
        switch (operation) {
            case EQUAL: {
                SocksEntity socksEntity = new SocksEntity(color, cottonPart);
                result = socksDao.getNumberTypeOfSocksInTheBox(socksEntity);
            }
                break;
            case MORETHEN: {
                for (int i = cottonPart+1; i <= 100; i++) {
                    SocksEntity socksEntity = new SocksEntity(color, i);
                    result += socksDao.getNumberTypeOfSocksInTheBox(socksEntity);
                }
            }
            break;
            case LESSTHEN: {
                for (int i = cottonPart - 1; i >= 0; i--) {
                    SocksEntity socksEntity = new SocksEntity(color, i);
                    result += socksDao.getNumberTypeOfSocksInTheBox(socksEntity);
                }
            }
            break;
            default:
                break;
        }
        return result;
    }
}

