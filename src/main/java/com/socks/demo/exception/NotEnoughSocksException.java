package com.socks.demo.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.util.NoSuchElementException;

@Component
@Getter
@Setter
@RequiredArgsConstructor
public class NotEnoughSocksException extends NoSuchElementException {

    private String errorMessage;
    private int errorCode;

    public NotEnoughSocksException(int quantity) {
        errorMessage  = "НЕДОСТАТОЧНО НОСКОВ НА СКЛАДЕ!!! Доступное количество : " + quantity;
    }

}
