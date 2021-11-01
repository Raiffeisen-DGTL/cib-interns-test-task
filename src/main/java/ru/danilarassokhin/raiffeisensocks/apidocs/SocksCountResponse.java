package ru.danilarassokhin.raiffeisensocks.apidocs;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import ru.danilarassokhin.raiffeisensocks.dto.ResponseDto;

@ApiModel("SocksCountResponse")
public class SocksCountResponse extends ResponseDto<Long> {

  @ApiModelProperty(example = "OK")
  private String status;

  public SocksCountResponse(String status, Long data) {
    super(status, data);
  }

}
