package com.yms.sockswarehouse.models;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * Class that represent socks objects.
 */

@Entity
@Table(name = "Socks")
public class Socks{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long Id;


    @Column(name = "color")
    @NotBlank(message = "Color cannot be empty.")
    @NotNull(message = "Color cannot be null.")
    private String color;


    @Column(name = "cottonpart")
    @Min(value = 0, message = "CottonPart cannot be less than {value}.")
    @Max(value = 100, message = "CottonPart cannot be more than {value}.")
    @NotNull(message = "CottonPart cannot be null.")
    private int cottonpart;

    @Min(value = 0, message = "Quantity cannot be less than {value}.")
    @NotNull(message = "Quantity cannot be null.")
    @Column(name = "quantity")
    private Long quantity;

    public Socks() {
    }

    public Socks(String color, int cottonpart, Long quantity) {
        this.color = color;
        this.cottonpart = cottonpart;
        this.quantity = quantity;
    }

    public Socks(Long id, String color, int cottonpart, Long quantity) {
        Id = id;
        this.color = color;
        this.cottonpart = cottonpart;
        this.quantity = quantity;
    }

    public Long getId() {
        return Id;
    }

    private void setId(Long id) {
        Id = id;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getCottonPart() {
        return cottonpart;
    }

    public void setCottonPart(int cottonPart) {
        this.cottonpart = cottonPart;
    }

    public Long getQuantity() {
        return quantity;
    }

    public void setQuantity(Long quantity) {
        this.quantity = quantity;
    }
}
