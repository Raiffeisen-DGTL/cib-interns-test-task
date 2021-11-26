package ru.sillyseal.raiffeisentask.model;

import java.io.Serializable;
import java.util.Objects;

//Класс для представления составного ключа
public class SockId implements Serializable {
    private String color;
    private int cotton_part;

    public SockId() { }

    public SockId(String color, int cottonPart) {
        this.color = color;
        this.cotton_part = cottonPart;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SockId sockId = (SockId) o;
        return cotton_part == sockId.cotton_part && color.equals(sockId.color);
    }

    @Override
    public int hashCode() {
        return Objects.hash(color, cotton_part);
    }
}
