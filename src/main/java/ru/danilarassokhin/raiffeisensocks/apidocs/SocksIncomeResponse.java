package ru.danilarassokhin.raiffeisensocks.apidocs;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import ru.danilarassokhin.raiffeisensocks.dto.ResponseDto;
import ru.danilarassokhin.raiffeisensocks.dto.SocksIncomeDto;

@ApiModel("SocksResponse")
public class SocksIncomeResponse extends ResponseDto<SocksIncomeDto> {

  @ApiModelProperty(example = "OK")
  private String status;

  public SocksIncomeResponse(String status, SocksIncomeDto data) {
    super(status, data);
  }

}
