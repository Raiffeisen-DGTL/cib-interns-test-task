package ru.vsu.rest.model;

import java.util.List;

public class ErrorModel {

  private List<ValidationError> errors;

  public ErrorModel(List<ValidationError> errors) {
    this.errors = errors;
  }

  public List<ValidationError> getErrors() {
    return errors;
  }

  public void setErrors(List<ValidationError> errors) {
    this.errors = errors;
  }
}
