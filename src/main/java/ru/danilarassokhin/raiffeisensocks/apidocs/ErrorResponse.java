package ru.danilarassokhin.raiffeisensocks.apidocs;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import ru.danilarassokhin.raiffeisensocks.dto.ResponseDto;

/**
 * Error response.
 */
@ApiModel("Error response")
public class ErrorResponse extends ResponseDto<String> {

  @ApiModelProperty(example = "INVALID_DATA")
  private String status;

  @ApiModelProperty(example = "Internal error occurred")
  private String data;

  public ErrorResponse(String status, String data) {
    super(status, data);
  }
}
