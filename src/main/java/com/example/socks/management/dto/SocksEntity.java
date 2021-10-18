package com.example.socks.management.dto;

import java.util.Objects;

public class SocksEntity {
    private Color color;
    private Integer cottonPart;

    public SocksEntity(Color color, Integer cottonPart) {
        this.color = color;
        this.cottonPart = cottonPart;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public Integer getCottonPart() {
        return cottonPart;
    }

    public void setCottonPart(Integer cottonPart) {
        this.cottonPart = cottonPart;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SocksEntity that = (SocksEntity) o;
        return color.equals(that.color) && cottonPart.equals(that.cottonPart);
    }

    @Override
    public int hashCode() {
        return Objects.hash(color, cottonPart);
    }
}
