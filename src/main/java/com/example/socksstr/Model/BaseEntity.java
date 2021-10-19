package com.example.socksstr.Model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


import java.io.Serializable;
import java.util.Objects;

@Getter
@Setter
@ToString
public class BaseEntity implements Serializable {
    private String color;
    private long cottonPart;

    public BaseEntity() {}

    public BaseEntity (String color, long cottonPart) {
        this.color = color;
        this.cottonPart = cottonPart;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BaseEntity id = (BaseEntity) o;
        return color.equals(id.color) &&
                cottonPart == id.cottonPart;
    }

    @Override
    public int hashCode() {
        return Objects.hash(color, cottonPart);
    }

}
