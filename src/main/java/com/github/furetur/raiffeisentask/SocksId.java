package com.github.furetur.raiffeisentask;

import javax.persistence.Column;
import java.io.Serializable;
import java.util.Objects;

public class SocksId implements Serializable {
    @Column(nullable = false)
    private String color;
    @Column(name = "cotton_part", nullable = false)
    private Integer cottonPart;

    protected SocksId() {}

    public SocksId(String color, Integer cottonPart) {
        this.color = color;
        this.cottonPart = cottonPart;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SocksId that = (SocksId) o;
        return Objects.equals(color, that.color) && Objects.equals(cottonPart, that.cottonPart);
    }

    @Override
    public int hashCode() {
        return Objects.hash(color, cottonPart);
    }
}
