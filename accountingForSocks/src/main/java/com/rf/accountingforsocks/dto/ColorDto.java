package com.rf.accountingforsocks.dto;

import java.util.UUID;

public class ColorDto {
    private UUID id;

    private String color;

    public ColorDto() {
    }

    public ColorDto(UUID id, String color) {
        this.id = id;
        this.color = color;
    }

    public UUID getId() {
        return id;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }


}
