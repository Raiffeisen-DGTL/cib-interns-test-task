package ru.danilarassokhin.raiffeisensocks.service.dto;

/**
 * Constant statuses of {@link ru.danilarassokhin.raiffeisensocks.service}.
 */
public enum ServiceResponseStatus {

  /**
   * No errors occurred in service.
   */
  OK,
  /**
   * Expected error occurred in service.
   */
  EXPECTED_ERROR,
  /**
   * Internal error occurred in service.
   */
  INTERNAL_ERROR,
  /**
   * Invalid income data.
   */
  INVALID_DATA,

  /*
  SocksService response statuses
   */
  /**
   * There is no socks exists with given data.
   */
  NO_SOCKS_EXIST,
  /**
   * There is no socks left with given data.
   */
  NO_SOCKS_LEFT,

  ;
}
