package alaev.dev.raiffeisentesttask.exception;

public class InvalidQuantity extends RuntimeException {

  public InvalidQuantity(String message) {
    super(String.format("The number of pairs of socks, an integer greater than 0. You receive: %s",
                        message));
  }
}
