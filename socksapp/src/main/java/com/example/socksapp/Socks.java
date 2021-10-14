package com.example.socksapp;

import com.sun.istack.NotNull;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Positive;


/**
 * Класс клиенты бизнес-центра
 */
@Entity
@Table(name = "socks")
public class Socks {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    /** Поле для хранения цвета носков*/
    @NotNull
    @Column(name = "color")
    private String color;

    /** Поле для хранения процентного содержания хлопка в
     * составе носков*/
    @Min(0)
    @Max(100)
    @Column(name = "cotton_part")
    private Integer cottonPart;

    /** Поле для хранения количества пар носков*/
    @Column(name = "quantity")
    @Positive
    private long quantity;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public void setCottonPart(Integer cottonPart) {
        this.cottonPart = cottonPart;
    }

    public long getQuantity() {
        return quantity;
    }

    public void setQuantity(long quantity) {
        this.quantity = quantity;
    }
}
