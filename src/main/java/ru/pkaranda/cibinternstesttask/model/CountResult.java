package ru.pkaranda.cibinternstesttask.model;


import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@JsonInclude
public class CountResult {

    private String color;
    private String operation;
    private int cottonPart;
    private int quantity;
}
