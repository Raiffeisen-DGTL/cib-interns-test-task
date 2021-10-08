package ru.danilarassokhin.raiffeisensocks.model;

import javax.persistence.*;

@Entity(name = "sock")
public class Sock {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Id
    @Column(name = "color")
    private SockColor sockColor;

    @Id
    @Column(name = "cotton_part")
    private byte cottonPart;

    @Column(name = "quantity")
    private Long quantity;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public SockColor getSockColor() {
        return sockColor;
    }

    public void setSockColor(SockColor sockColor) {
        this.sockColor = sockColor;
    }

    public byte getCottonPart() {
        return cottonPart;
    }

    public void setCottonPart(byte cottonPart) {
        this.cottonPart = cottonPart;
    }

    public Long getQuantity() {
        return quantity;
    }

    public void setQuantity(Long quantity) {
        this.quantity = quantity;
    }
}
