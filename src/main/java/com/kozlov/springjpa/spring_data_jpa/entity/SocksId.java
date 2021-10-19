package com.kozlov.springjpa.spring_data_jpa.entity;

import java.io.Serializable;
import java.util.Objects;

public class SocksId implements Serializable {

    private String color;

    private int cottonPart;

    public SocksId(String color, int cottonPart) {
        this.color = color;
        this.cottonPart = cottonPart;
    }

    public SocksId() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SocksId socksId = (SocksId) o;
        return cottonPart == socksId.cottonPart && Objects.equals(color, socksId.color);
    }

    @Override
    public int hashCode() {
        return Objects.hash(color, cottonPart);
    }
}
