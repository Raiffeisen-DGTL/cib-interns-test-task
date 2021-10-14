package proger.savcheg.cibinternstesttask;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import static java.util.Objects.hash;

@Entity
@Table(name = "sockslist")
public class Sock {
    @Id
    @Column(name = "id", nullable = false)
    private int id;
    @Column(name = "color", nullable = false)
    private String color;
    @Column(name = "cottonpart", nullable = false)
    private int cottonPart;
    @Column(name = "quantity", nullable = false)
    private int quantity;

    public Sock() {
    }

    public Sock(String color, int cottonPart, int quantity) {
        this.color = color;
        this.cottonPart = cottonPart;
        this.quantity = quantity;
        this.id = hash(color, cottonPart);
    }

    @JsonIgnore
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCottonPart() {
        return cottonPart;
    }

    public void setCottonPart(int cottonPart) {
        this.cottonPart = cottonPart;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Sock sock = (Sock) o;
        return id == sock.id && cottonPart == sock.cottonPart && color.equals(sock.color);
    }

    @Override
    public int hashCode() {
        return hash(color, cottonPart);
    }

    @Override
    public String toString() {
        return "Sock{" +
                "id=" + id +
                ", color='" + color + '\'' +
                ", cottonPart=" + cottonPart +
                ", quantity=" + quantity +
                '}';
    }
}
