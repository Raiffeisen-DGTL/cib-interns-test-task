package alaev.dev.raiffeisentesttask.exception;

public class NotEnoughSocksException extends RuntimeException {

  public NotEnoughSocksException(String message) {
    super(String.format("There are not so many socks in stock: %s", message));
  }
}
