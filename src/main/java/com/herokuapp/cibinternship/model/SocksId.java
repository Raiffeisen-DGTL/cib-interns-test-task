package com.herokuapp.cibinternship.model;

import java.io.Serializable;
import java.util.Objects;

public class SocksId implements Serializable {
    private String color;
    private int cottonPart;

    public SocksId() {}

    public SocksId(String color, int cottonPart) {
        this.color = color;
        this.cottonPart = cottonPart;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SocksId sockId = (SocksId) o;
        return color.equals(sockId.color) &&
                cottonPart == sockId.cottonPart;
    }

    @Override
    public int hashCode() {
        return Objects.hash(color, cottonPart);
    }
}
