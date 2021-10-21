package ru.dnsk.accountingofsocks.model;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "pair_of_socks")
public class PairOfSocks {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "color")
    @NotBlank(message = "Color not entered")
    private String color;

    @Column(name = "cotton_part")
    @Max(value = 100, message = "The percentage of cotton must be between 0 and 100")
    @Min(value = 0, message = "The percentage of cotton must be between 0 and 100")
    private int cottonPart;

    @Column(name = "quantity")
    @Min(value = 1)
    private int quantity;

    public PairOfSocks() {
    }

    public PairOfSocks(String color, int cottonPart, int quantity) {
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
}
