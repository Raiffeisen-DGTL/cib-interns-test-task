package com.intern.sock.implservice;

import com.intern.sock.enums.ErrorEnum;
import com.intern.sock.exceptions.UrlException;
import com.intern.sock.model.Sock;
import lombok.NonNull;
import java.util.List;

public abstract class CheckService implements CheckCorrect{
    @Override
    public void checkQuantity(@NonNull Long quantity) throws UrlException {
        if(quantity<0){
            throw new UrlException(ErrorEnum.IncorrectURL);
        }
    }
    @Override
    public void checkCottonPart(@NonNull Long cottonPart) throws UrlException{
        if(cottonPart < 0 || cottonPart > 100){
            throw new UrlException(ErrorEnum.IncorrectURL);
        }
    }
    @Override
    public void allCheck(@NonNull Sock sock) throws UrlException{
        checkQuantity(sock.getQuantity());
        checkCottonPart(sock.getCottonPart());
    }
    public void checkTwoQuantity(@NonNull Long quantityFromRequest, @NonNull Long quatityFromDB) throws UrlException{
        if(quantityFromRequest > quatityFromDB) throw new UrlException(ErrorEnum.IncorrectURL);
    }
    public Long countQuantity(List<Sock> sockList){
        Long quantityRes = 0L;
        for(Sock sock:sockList){
            quantityRes+=sock.getQuantity();
        }
        return quantityRes;
    }
}
