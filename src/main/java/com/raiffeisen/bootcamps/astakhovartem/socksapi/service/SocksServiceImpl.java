package com.raiffeisen.bootcamps.astakhovartem.socksapi.service;


import com.raiffeisen.bootcamps.astakhovartem.socksapi.dao.SocksDAO;
import com.raiffeisen.bootcamps.astakhovartem.socksapi.entity.Socks;
import com.raiffeisen.bootcamps.astakhovartem.socksapi.exceptions.SocksIncorrectDataException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.function.Predicate;

@Service
public class SocksServiceImpl implements SocksService {

    public static final String EQUAL = "EQUAL";
    public static final String MORETHAN = "MORETHAN";
    public static final String LESSTHAN = "LESSTHAN";

    @Autowired
    private SocksDAO socksDAO;

    @Override
    public void increaseSocks(Socks socks) {

        Socks existsSocks = socksDAO.findSocksByColorAndCottonPart(socks.getColor(), socks.getCottonPart());
        if (existsSocks != null) {
            existsSocks.setQuantity(socks.getQuantity() + existsSocks.getQuantity());
            socksDAO.save(existsSocks);
        } else
            socksDAO.save(socks);
    }

    @Override
    public void decreaseSocks(Socks socks) {
        Socks existsSocks = socksDAO.findSocksByColorAndCottonPart(socks.getColor(), socks.getCottonPart());
        if (existsSocks != null) {
            if (existsSocks.getQuantity() >= socks.getQuantity()) {
                existsSocks.setQuantity(existsSocks.getQuantity() - socks.getQuantity());
                socksDAO.save(existsSocks);
            } else {
                throw new SocksIncorrectDataException("Lack of required quantity");
            }
        } else {
            throw new SocksIncorrectDataException("No socks found");
        }
    }

    private Predicate<Socks> getSocksFilter(String operation, final int cottonPart) {
        switch (operation.toUpperCase()) {
            case MORETHAN:
                return socks -> cottonPart < socks.getCottonPart();
            case LESSTHAN:
                return socks -> cottonPart > socks.getCottonPart();
            case EQUAL:
                return socks -> cottonPart == socks.getCottonPart();
            default:
                return socks -> false;
        }
    }

    @Override
    public int getQuantity(String color, String operation, int cottonPart) {
        Predicate<Socks> socksFilter = getSocksFilter(operation, cottonPart);
        List<Socks> socks = socksDAO.findSocksByColor(color);
        return socks.stream().filter(socksFilter).mapToInt(Socks::getQuantity).sum();
    }
}



