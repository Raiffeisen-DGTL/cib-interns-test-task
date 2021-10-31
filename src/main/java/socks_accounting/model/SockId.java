package socks_accounting.model;

import java.io.Serializable;
import java.util.Objects;

public class SockId implements Serializable {
    private String color;

    private int cottonPart;

    public SockId(String color, int cottonPart) {
        this.color = color;
        this.cottonPart = cottonPart;
    }

    public SockId() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SockId sockId = (SockId) o;
        return cottonPart == sockId.cottonPart && Objects.equals(
                color, sockId.color
        );
    }

    @Override
    public int hashCode() {
        return Objects.hash(color, cottonPart);
    }
}
