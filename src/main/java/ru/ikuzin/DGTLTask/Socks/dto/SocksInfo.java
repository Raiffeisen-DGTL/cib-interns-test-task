package ru.ikuzin.DGTLTask.Socks.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class SocksInfo {
    @NotEmpty
    @JsonProperty("color")
    private String color;

    @Min(0)
    @Max(100)
    @NotNull
    @JsonProperty("count")
    private Integer count;

    @Min(1)
    @Max(100)
    @NotNull
    @JsonProperty("cottonPart")
    private Integer cottonPart;
}
