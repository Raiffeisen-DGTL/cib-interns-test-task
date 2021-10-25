package ru.raiffeisen.soksapp.entity;

import ru.raiffeisen.soksapp.model.Socks;

import javax.persistence.*;

@Entity
@Table(name = "SOCKS")
public class SockEntity {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(name = "COLOR")
    private String color;

    @Column(name = "COTTON_PART")
    private Integer cottonPart;

    @Column(name = "QUANTITY")
    private Integer quantity;

    public SockEntity() {
    }

    public SockEntity(Socks sock) {
        this.color = sock.getColor();
        this.cottonPart = sock.getCottonPart();
        this.quantity = sock.getQuantity();
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public Integer getCottonPart() {
        return cottonPart;
    }

    public void setCottonPart(int cottonPart) {
        this.cottonPart = cottonPart;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Long getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Sock{" +
                "id='" + id + '\'' +
                "color='" + color + '\'' +
                ", cottonPart=" + cottonPart +
                ", quantity=" + quantity +
                '}';
    }
}
