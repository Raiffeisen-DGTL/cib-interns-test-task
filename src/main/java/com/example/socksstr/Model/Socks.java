package com.example.socksstr.Model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;


@Entity
@Table(name = "Socks")
@Getter
@Setter
@ToString
@IdClass(BaseEntity.class)
public class Socks extends BaseEntity {

    @Id
    @Column(name = "color")
    @NotNull
    private String color;

    @Id
    @Column(name = "cottonPart")
    @Min(0)
    @Max(100)
    private long cottonPart;

    @Column(name = "quantity")
    @Min(1)
    private long quantity;


    public Socks() {
    }

    public Socks(String color, long cottonPart, long quantity) {
        this.color = color;
        this.cottonPart = cottonPart;
        this.quantity = quantity;
    }

}
