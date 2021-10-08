package com.raiffeizen.demo.services;

import com.raiffeizen.demo.dao.SocksDao;
import com.raiffeizen.demo.exceptions.BadRequestException;
import com.raiffeizen.demo.exceptions.InternalServerErrorException;
import com.raiffeizen.demo.models.Socks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class SocksService {

    @Autowired
    private SocksDao socksDao;

    public long findByColorAndCottonPart(String color, String operation, int cottonPart) {
        switch (operation) {
            case "moreThan":
                return socksDao.findByColorWithMoreThanCottonPart(color, cottonPart).orElse(0L);
            case "lessThan":
                return socksDao.findByColorWithLessThanCottonPart(color, cottonPart).orElse(0L);
            case "equal":
                return socksDao.findByColorWithEqualCottonPart(color, cottonPart).orElse(0L);
            default:
                throw new BadRequestException();
        }
    }

    public void addSocks(Socks socks) {
        long remainingSocks = socksDao.findByColorWithEqualCottonPart(socks.getColor(), socks.getCottonPart()).orElse(0L);
        if (remainingSocks > 0) {
            socks.setQuantity(socks.getQuantity() + remainingSocks);
            socksDao.update(socks);
        }
        socksDao.save(socks);
    }

    public void removeSocks(Socks socks) {
        long remainingSocks = socksDao.findByColorWithEqualCottonPart(socks.getColor(), socks.getCottonPart()).orElse(0L);
        if (remainingSocks >= socks.getQuantity()) {
            socks.setQuantity(remainingSocks - socks.getQuantity());
            socksDao.update(socks);
        } else {
            throw new InternalServerErrorException();
        }
    }
}
