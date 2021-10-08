package ru.danilarassokhin.raiffeisensocks.model;

import java.io.Serializable;

public class SocksId implements Serializable {

    private Long id;

    private String color;

    private byte cottonPart;

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public byte getCottonPart() {
        return cottonPart;
    }

    public void setCottonPart(byte cottonPart) {
        this.cottonPart = cottonPart;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public int hashCode() {
        return id.intValue();
    }
}
