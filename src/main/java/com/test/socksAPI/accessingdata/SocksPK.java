package com.test.socksAPI.accessingdata;


import java.io.Serializable;
import java.util.Objects;

public class SocksPK implements Serializable {

    protected String color;
    protected byte cottonPart;

    public SocksPK() {}

    public SocksPK(String color, byte cottonPart) {
        this.color = color;
        this.cottonPart = cottonPart;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SocksPK socksPK = (SocksPK) o;
        return cottonPart == socksPK.cottonPart && color.equals(socksPK.color);
    }

    @Override
    public int hashCode() {
        return Objects.hash(color, cottonPart);
    }


}

