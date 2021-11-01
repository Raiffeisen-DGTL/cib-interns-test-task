package ru.danilarassokhin.raiffeisensocks.service.dto;

/**
 * Represents response from {@link ru.danilarassokhin.raiffeisensocks.service}.
 *
 * @param <P> Response's payload type
 */
public interface ServiceResponse<P> {

  /**
   * Returns response's payload.
   *
   * @return Payload object
   */
  P getPayload();

  /**
   * Sets response's payload.
   *
   * @param payload Object to use as payload
   */
  void setPayload(P payload);

  /**
   * Returns {@link ServiceResponseStatus}
   * of response.
   *
   * @return Response's status
   */
  ServiceResponseStatus getStatus();

  /**
   * Sets {@link ServiceResponseStatus}
   * for response.
   *
   * @param status Status of response
   */
  void setStatus(ServiceResponseStatus status);

  String getMessage();

  void setMessage(String message);

}
