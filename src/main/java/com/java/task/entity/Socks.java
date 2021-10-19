package com.java.task.entity;

import lombok.*;
import javax.persistence.*;

@Entity
@Table(name = "socks")
@Data
@NoArgsConstructor
public class Socks {
    @Id
    @Column(name = "socks_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "color")
    private int colorId;

    @Column(name = "cotton_part")
    private int cottonPart;

    @Column(name = "quantity")
    private int quantity;

    @ManyToOne()
    @JoinColumn(name = "color", referencedColumnName = "color_id", insertable = false, updatable = false)
    private Color color;

    public Socks(int colorId, int cottonPart, int quantity) {
        this.colorId = colorId;
        this.cottonPart = cottonPart;
        this.quantity = quantity;
    }
}
