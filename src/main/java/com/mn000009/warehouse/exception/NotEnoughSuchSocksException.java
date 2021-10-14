package com.mn000009.warehouse.exception;

public class NotEnoughSuchSocksException extends RuntimeException {

  public NotEnoughSuchSocksException(String message) {
    super("SocksPackage were not found in the right volume.\nThere are only " + message + " socks in stock");
  }

}
