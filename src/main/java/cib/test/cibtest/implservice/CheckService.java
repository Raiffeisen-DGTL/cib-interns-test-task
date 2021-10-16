package cib.test.cibtest.implservice;

import cib.test.cibtest.enums.EnumForError;
import cib.test.cibtest.exceptions.excclass.UrlException;
import cib.test.cibtest.model.Sock;
import cib.test.cibtest.service.CheckForCorrect;
import lombok.NonNull;

import java.util.List;

public abstract class CheckService implements CheckForCorrect {

    @Override
    public void checkQuantity(@NonNull Long quantity) throws UrlException {
        if (quantity < 0) {
            throw new UrlException(EnumForError.IncorrectURL);
        }
    }

    @Override
    public void checkCottonPart(@NonNull Long cottonPart) throws UrlException {
        if (cottonPart < 0 || cottonPart  > 100) {
            throw new UrlException(EnumForError.IncorrectURL);
        }
    }

    @Override
    public void fullCheck(@NonNull Sock sock) throws UrlException {
        checkCottonPart(sock.getCottonPart());
        checkQuantity(sock.getQuantity());
    }

    public void checkTwoQuantity(@NonNull Long quantityFromRequest, @NonNull Long quantityFromDB) throws UrlException {
        if (quantityFromRequest > quantityFromDB) throw new UrlException(EnumForError.IncorrectURL);
    }
    public Long countQuantity(List<Sock> sockList){
        Long quantityRes=0L;
        for (Sock sock:sockList){
            quantityRes+=sock.getQuantity();
        }
        return quantityRes;
    }
}
