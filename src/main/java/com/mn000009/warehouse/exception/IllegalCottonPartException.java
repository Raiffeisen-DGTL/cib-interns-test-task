package com.mn000009.warehouse.exception;

public class IllegalCottonPartException extends IllegalArgumentException {

  public IllegalCottonPartException(String s) {
    super("Received field cottonPart is incorrect: value must be between 0-100. Current value - " + s);
  }

}
