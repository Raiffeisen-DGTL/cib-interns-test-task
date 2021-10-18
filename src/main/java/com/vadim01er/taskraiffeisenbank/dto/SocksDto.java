package com.vadim01er.taskraiffeisenbank.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Null;

@Getter
@Setter
@Validated
@ToString
public class SocksDto {
    @Null
    private Long id;

    @NotBlank
    private String color;

    @Min(0)
    @Max(100)
    private Short cottonPart;

    @Min(0)
    private Long quality;

}
