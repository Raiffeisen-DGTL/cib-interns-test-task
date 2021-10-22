package ru.pkaranda.cibinternstesttask.model;


import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CountResult {

    private String color;
    private String operation;
    private Integer cottonPart;
    private Integer quantity;
}
