package ru.danilarassokhin.raiffeisensocks.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * Represents response for request
 * @param <O> Response data type
 */
@ApiModel("Response")
public class ResponseDto<O> {

    @ApiModelProperty(name = "Response message", example = "Success")
    private String message;

    @ApiModelProperty(name = "Response data")
    private O data;

    public ResponseDto(String message) {
        this.message = message;
    }

    public ResponseDto(O data) {
        this.data = data;
        this.message = "";
    }

    public ResponseDto(String message, O data) {
        this.message = message;
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public O getData() {
        return data;
    }

    public void setData(O data) {
        this.data = data;
    }
}
