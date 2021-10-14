package com.mn000009.warehouse.exception;

public class IllegalQuantityException extends IllegalArgumentException {

  public IllegalQuantityException(String s) {
    super("Received field quantity is incorrect: value must be greater 0. Current value - " + s);
  }

}
