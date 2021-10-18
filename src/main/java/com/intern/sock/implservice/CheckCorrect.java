package com.intern.sock.implservice;
import com.intern.sock.exceptions.UrlException;
import com.intern.sock.model.Sock;
import lombok.NonNull;

public interface CheckCorrect {
    void checkQuantity(@NonNull Long quatity) throws UrlException;
    void checkCottonPart(@NonNull Long cottonPart) throws UrlException;
    void allCheck(@NonNull Sock sock) throws UrlException;
    void checkTwoQuantity(@NonNull Long quantityFromRequest, @NonNull Long quantityFromDB) throws UrlException;
}
