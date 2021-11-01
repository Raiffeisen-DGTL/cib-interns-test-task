package ru.danilarassokhin.raiffeisensocks.exception;

import org.springframework.http.HttpStatus;

/**
 * Represents abstract exception of some request.
 */
public class RequestException extends Exception {

  private HttpStatus responseStatus;

  public RequestException(String message) {
    super(message);
    responseStatus = HttpStatus.INTERNAL_SERVER_ERROR;
  }

  public RequestException(String message, HttpStatus httpStatus) {
    super(message);
    this.responseStatus = httpStatus;
  }

  public HttpStatus getResponseStatus() {
    return responseStatus;
  }

  public void setResponseStatus(HttpStatus responseStatus) {
    this.responseStatus = responseStatus;
  }

}
