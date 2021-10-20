package com.raiffeisen.test.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "socks")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class SockBatch {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "color")
    private String color;

    @Column(name = "cotton_part")
    private int cottonPart;

    @Column(name = "quantity")
    private int quantity;

    @Column(name = "created_at")
    @CreationTimestamp
    private Date createdAt;

    @Column(name = "updated_at")
    @UpdateTimestamp
    private Date updatedAt;

    public SockBatch(String color, int cottonPart, int quantity) {
        this.color = color;
        this.cottonPart = cottonPart;
        this.quantity = quantity;
    }

    public SockBatch(String color, int cottonPart) {
        this.color = color;
        this.cottonPart = cottonPart;
    }

}
