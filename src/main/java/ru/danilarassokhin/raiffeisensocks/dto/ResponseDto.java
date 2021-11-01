package ru.danilarassokhin.raiffeisensocks.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModelProperty;

/**
 * Represents response for request.
 *
 * @param <O> Response data type
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResponseDto<O> {

  @ApiModelProperty(name = "Response status", example = "OK")
  private String status;

  @ApiModelProperty(name = "Response data")
  private O data;

  @ApiModelProperty(name = "Response message")
  private String message;

  public ResponseDto(String status) {
    this.status = status;
  }

  public ResponseDto(O data) {
    this.data = data;
    this.status = "";
  }

  public ResponseDto(String status, O data) {
    this.status = status;
    this.data = data;
  }

  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }

  public O getData() {
    return data;
  }

  public void setData(O data) {
    this.data = data;
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }
}
