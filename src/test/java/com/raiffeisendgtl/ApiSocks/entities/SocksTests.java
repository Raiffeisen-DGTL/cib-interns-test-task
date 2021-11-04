package com.raiffeisendgtl.ApiSocks.entities;

import com.raiffeisendgtl.ApiSocks.components.SocksErrorCode;
import com.raiffeisendgtl.ApiSocks.components.SocksException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class SocksTests {

    @Test
    public void socksConstructor() {
        String color = "red";
        int cottonPart = 25;
        int quantity = 5;

        Socks result = new Socks(color, cottonPart, quantity);

        assertEquals(color, result.getColor());
        assertEquals(cottonPart, result.getCottonPart());
        assertEquals(quantity, result.getQuantity());
    }

    @Test
    public void addQuantityTest() {
        Socks socks = new Socks("black", 30, 3);
        int quantity = 7;
        int expectedResult = 10;

        socks.addQuantity(quantity);

        assertEquals(expectedResult, socks.getQuantity());
    }

    @Test
    public void subtractQuantityIsCorrect() {
        Socks socks = new Socks("white", 50, 10);
        int quantity = 9;
        int expectedResult = 1;

        socks.subtractQuantity(quantity);

        assertEquals(expectedResult, socks.getQuantity());
    }

    @Test
    public void subtractQuantityIsError() {
        Socks socks = new Socks("purple", 99, 9);
        int quantity = 13;
        SocksErrorCode expectedResult = SocksErrorCode.INCORRECT_PARAMS;

        SocksException exception = assertThrows(SocksException.class, () -> {
            socks.subtractQuantity(quantity);
        });

        assertEquals(expectedResult, exception.getError());
    }

}
