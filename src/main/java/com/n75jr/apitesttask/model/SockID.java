package com.n75jr.apitesttask.model;

import java.io.Serializable;
import java.util.Objects;

public class SockID implements Serializable {
    private String color;
    private int cottonPart;

    public SockID() {
    }

    public SockID(String color, int cottonPart) {
        this.color = color;
        this.cottonPart = cottonPart;
    }

    @Override
    public boolean equals(Object otherObj) {
        if (this == otherObj) {
            return true;
        }
        if (otherObj == null) {
            return false;
        }
        if (getClass() != otherObj.getClass()) {
            return false;
        }
        SockID other = (SockID) otherObj;
        return Objects.equals(color, other.color) && cottonPart == other.cottonPart;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(color) + 17 * cottonPart;
    }

    @Override
    public String toString() {
        return "SockId{" +
                "color='" + color + '\'' +
                ", cottonPart=" + cottonPart +
                '}';
    }
}