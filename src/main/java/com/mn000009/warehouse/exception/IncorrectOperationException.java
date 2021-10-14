package com.mn000009.warehouse.exception;

public class IncorrectOperationException extends RuntimeException {

  public IncorrectOperationException(String operation) {
    super("Incorrect operation: " + operation.toString() + ".\nValid operations: moreThan, lessThan, equal");
  }

}
