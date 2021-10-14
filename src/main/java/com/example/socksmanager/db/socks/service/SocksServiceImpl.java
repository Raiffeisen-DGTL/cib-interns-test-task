package com.example.socksmanager.db.socks.service;

import com.example.socksmanager.db.socks.dao.SocksDao;
import com.example.socksmanager.model.exceprion.NotEnoughSocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class SocksServiceImpl implements SocksService {

    private SocksDao socksDao;

    @Autowired
    public SocksServiceImpl(SocksDao socksDao) {
        this.socksDao = socksDao;
    }

    @Override
    public void incomeSocks(String color, int cottonPart, int quantity) {
        if (socksDao.getEqualCount(color, cottonPart) == 0) {
            socksDao.insertSocks(color, cottonPart, quantity);
        } else {
            socksDao.incomeSocks(color, cottonPart, quantity);
        }
    }

    @Override
    public void outcomeSocks(String color, int cottonPart, int quantity) throws NotEnoughSocks {
        int socksCount = socksDao.getEqualCount(color, cottonPart);
        if (socksCount >= quantity) socksDao.outcomeSocks(color, cottonPart, quantity);
        else throw new NotEnoughSocks();
    }

    @Override
    public int getSocksCount(String color, String operation, int cottonPart) {
        int count = 0;

        if (operation.equals("moreThan")) {
            count = socksDao.getMoreCount(color, cottonPart);
        } else if (operation.equals("equal")) {
            count = socksDao.getEqualCount(color, cottonPart);
        } else if (operation.equals("lessThan")) {
            count = socksDao.getLessCount(color, cottonPart);
        }

        return count;
    }
}
