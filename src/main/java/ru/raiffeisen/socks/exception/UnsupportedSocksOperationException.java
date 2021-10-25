package ru.raiffeisen.socks.exception;

public class UnsupportedSocksOperationException extends RuntimeException {
    public UnsupportedSocksOperationException() {
        super("Неподдерживаемый оператор сравнения значения количества хлопка в составе носков");
    }
}
