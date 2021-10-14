package com.rf.accountingforsocks.entity;


import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "socks")
public class Socks {
    @Id
    UUID id;

    @PrePersist
    private void setId() {
        if(this.id == null) {
            this.id = UUID.randomUUID();
        }
    }

    @ManyToOne
    @JoinColumn(name = "color_id")
    private Color color;

    @Column(name = "cotton_part")
    private Integer cottonPart;

    @Column(name = "quantity")
    private Integer quantity;

    public Socks() {
    }

    public Socks(UUID id, Color color, Integer cottonPart, Integer quantity) {
        this.id = id;
        this.color = color;
        this.cottonPart = cottonPart;
        this.quantity = quantity;
    }

    public void income(Integer quantity) {
        this.quantity += quantity;
    }

    public boolean outcome(Integer quantity) {
        if(this.quantity - quantity < 0) {
            return false;
        }
        this.quantity -= quantity;
        return true;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public Integer getCottonPart() {
        return cottonPart;
    }

    public void setCottonPart(Integer cottonPart) {
        this.cottonPart = cottonPart;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}
