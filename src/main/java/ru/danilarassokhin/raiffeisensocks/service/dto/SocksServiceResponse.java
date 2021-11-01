package ru.danilarassokhin.raiffeisensocks.service.dto;

import ru.danilarassokhin.raiffeisensocks.service.SocksService;

/**
 * Represents response from {@link SocksService}
 */
public class SocksServiceResponse implements ServiceResponse<Object> {

  private ServiceResponseStatus socksServiceResponseStatus;
  private Object payload;
  private String message;

  public SocksServiceResponse() {
    this.message = "";
    this.socksServiceResponseStatus = ServiceResponseStatus.OK;
  }

  @Override
  public Object getPayload() {
    return payload;
  }

  @Override
  public void setPayload(Object payload) {
    this.payload = payload;
  }

  @Override
  public ServiceResponseStatus getStatus() {
    return socksServiceResponseStatus;
  }

  @Override
  public void setStatus(ServiceResponseStatus status) {
    this.socksServiceResponseStatus = status;
  }

  @Override
  public String getMessage() {
    return message;
  }

  @Override
  public void setMessage(String message) {
    this.message = message;
  }

}
