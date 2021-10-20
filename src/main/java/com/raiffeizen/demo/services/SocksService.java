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
                return  socksDao.findQuantityByColorWithMoreThanCottonPart(color, cottonPart);
            case "lessThan":
                return socksDao.findQuantityByColorWithLessThanCottonPart(color, cottonPart);
            case "equal":
                return socksDao.findQuantityByColorWithEqualCottonPart(color, cottonPart);
            default:
                throw new BadRequestException();
        }
    }

    public void addSocks(Socks socks) {
        if (socks.getQuantity() < 0) {
            throw new BadRequestException();
        }
        long quantityOfRemainingSocks =  socksDao.findQuantityByColorWithEqualCottonPart(socks.getColor(), socks.getCottonPart());
        if (quantityOfRemainingSocks > 0) {
            Socks remainingSocks = socksDao.findByColorAndCottonPart(socks.getColor(), socks.getCottonPart()).orElseThrow(InternalServerErrorException::new);
            remainingSocks.setQuantity(socks.getQuantity() + remainingSocks.getQuantity());
        } else {
            socksDao.save(socks);
        }
    }

    public void removeSocks(Socks socks) {
        if (socks.getQuantity() < 0) {
            throw new BadRequestException();
        }
        long quantityOfRemainingSocks = socksDao.findQuantityByColorWithEqualCottonPart(socks.getColor(), socks.getCottonPart());
        if (quantityOfRemainingSocks >= socks.getQuantity()) {
            Socks remainingSocks = socksDao.findByColorAndCottonPart(socks.getColor(), socks.getCottonPart()).orElseThrow(InternalServerErrorException::new);
            remainingSocks.setQuantity(quantityOfRemainingSocks - socks.getQuantity());
            if (remainingSocks.getQuantity() == 0) {
                socksDao.delete(remainingSocks);
            }
        } else {
            throw new BadRequestException();
        }
    }
}
