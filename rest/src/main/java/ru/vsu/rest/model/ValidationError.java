package ru.vsu.rest.model;

public class ValidationError {

  private String message;

  public ValidationError(String message) {
    this.message = message;
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }
}
