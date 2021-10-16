package cib.test.cibtest.service;

import cib.test.cibtest.exceptions.excclass.UrlException;
import cib.test.cibtest.model.Sock;
import lombok.NonNull;

public interface CheckForCorrect {
    void checkQuantity(@NonNull Long quantity) throws UrlException;
    void checkCottonPart(@NonNull Long cottonPart) throws UrlException;
    void fullCheck(@NonNull Sock sock) throws  UrlException;
    void checkTwoQuantity(@NonNull Long quantityFromRequest, @NonNull Long quantityFromDB) throws UrlException;
}