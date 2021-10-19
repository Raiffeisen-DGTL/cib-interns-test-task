package com.fedoseeva.socksaccountingapp.dto;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class SelectDTORequest {

    @NotEmpty
    String color;

    @NotEmpty
    String operation;

    @NotNull
    @Min(0)
    @Max(100)
    int cottonPart;

    public SelectDTORequest(String color, String operation, int cottonPart) {
        this.color = color;
        this.operation = operatorDefinition(operation);
        this.cottonPart = cottonPart;
    }

    private String operatorDefinition(String operation) {
        if (operation == null) {
            return "";
        }
        switch (operation) {
            case "moreThan":
                return ">";
            case "lessThan":
                return "<";
            case "equal":
                return "=";
            default:
                return "";
        }
    }

    public String getColor() {
        return color;
    }

    public String getOperation() {
        return operation;
    }

    public int getCottonPart() {
        return cottonPart;
    }
}
