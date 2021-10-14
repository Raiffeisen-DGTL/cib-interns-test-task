package com.mn000009.warehouse.exception;

public class IllegalColorException extends IllegalArgumentException {

  public IllegalColorException(String s) {
    super("Сolor field cannot be empty or not specified.\nCurrent value of color - " + s);
  }

}
