package alaev.dev.raiffeisentesttask.exception;

public class InvalidCottonPart extends RuntimeException {

  public InvalidCottonPart(String message) {
    super(String.format(
        "Percentage of cotton (in the composition of socks from 0 to 100. You receive: %s",
        message));
  }
}
