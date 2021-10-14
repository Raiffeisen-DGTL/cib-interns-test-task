package com.example.interntask;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class SocksServiceIml implements SocksService {
    private SocksInterface sInt;

    @Autowired
    public SocksServiceIml(SocksInterface sArg){this.sInt = sArg;}

    @Override
    public void income(SocksByType socks) {
        List<SocksByType> res = sInt.findAllByColorAndCottonPartEquals(socks.getColor(), socks.getCottonPart());
        if(res.isEmpty()){
            socks.setType_id(socks.getColor().toString().toLowerCase() + socks.getCottonPart().toString());
            sInt.save(socks);
        }
        else{
            SocksByType resQuery = res.get(0);
            resQuery.setQuantity(resQuery.getQuantity() + socks.getQuantity());
            sInt.save(resQuery);
        }
    }

    @Override
    public void outcome(SocksByType socks) {
        List<SocksByType> res = sInt.findAllByColorAndCottonPartEquals(socks.getColor(), socks.getCottonPart());
        if(res.isEmpty()){
            throw new IllegalArgumentException();
        }
        else{
            SocksByType resQuery = res.get(0);
            if(resQuery.getQuantity() < socks.getQuantity()){
                throw new IllegalArgumentException();
            }
            resQuery.setQuantity(resQuery.getQuantity() - socks.getQuantity());
            if(resQuery.getQuantity() != 0){
                sInt.save(resQuery);
            }
            else{
                sInt.delete(resQuery);
            }
        }
    }

    @Override
    public Integer getNumberSocks(String color, String operation, Integer cotton_part) {
        List<SocksByType> toCount = null;
        if(Objects.equals(operation, "<")){
            toCount = sInt.findAllByColorAndCottonPartIsLessThan(color, cotton_part);
        }
        else if(Objects.equals(operation, ">")){
            toCount = sInt.findAllByColorAndCottonPartGreaterThan(color, cotton_part);
        }
        else if(Objects.equals(operation, "=")){
            toCount = sInt.findAllByColorAndCottonPartEquals(color, cotton_part);
        }
        Integer res = 0;
        if(toCount != null) {
            for (SocksByType s : toCount) {
                res += s.getQuantity();
            }
        }
        return res;
    }
}
