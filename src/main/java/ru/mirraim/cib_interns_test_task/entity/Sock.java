package ru.mirraim.cib_interns_test_task.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "socks")
public class Sock {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "color")
    private String color;
    @Column(name = "cotton_part")
    private int cottonPart;
    @Column(name = "quantity")
    private int quantity;

    public Sock() {
    }

    public Sock(String color, int cottonPart, int quantity) {
        this.color = color;
        this.cottonPart = cottonPart;
        this.quantity = quantity;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Sock sock = (Sock) o;
        return id == sock.id && Objects.equals(color, sock.color) && Objects.equals(cottonPart, sock.cottonPart);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, color, cottonPart);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Socks{");
        sb.append("id=").append(id);
        sb.append(", color='").append(color).append('\'');
        sb.append(", cottonPart='").append(cottonPart).append('\'');
        sb.append(", quantity=").append(quantity);
        sb.append('}');
        return sb.toString();
    }
}
