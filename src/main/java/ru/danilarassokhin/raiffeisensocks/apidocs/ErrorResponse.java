package ru.danilarassokhin.raiffeisensocks.apidocs;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import ru.danilarassokhin.raiffeisensocks.dto.ResponseDto;

@ApiModel("Error response")
public class ErrorResponse extends ResponseDto<String> {

    @ApiModelProperty(example = "Error")
    private String message;

    @ApiModelProperty(example = "Internal error occurred")
    private String data;

    public ErrorResponse(String message, String data) {
        super(message, data);
    }
}
