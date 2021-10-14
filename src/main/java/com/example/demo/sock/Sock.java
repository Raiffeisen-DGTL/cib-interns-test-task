package com.example.demo.sock;

import javax.persistence.*;
import javax.validation.constraints.*;

@Entity
@Table
public class Sock {

    @Id
    @SequenceGenerator(
            name = "sock_sequence",
            sequenceName = "sock_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "sock_sequence"
    )
    private Long id;

    @Pattern(regexp = "^[a-zA-Z]+$")
    @NotBlank
    private String color;

    @Min(1) @Max(100)
    @NotNull
    private Byte cottonPart;

    @Min(1)
    @NotNull
    private Short quantity;

    public Sock() {
    }

    public Sock(Long id, String color, Byte cottonPart, Short quantity) {
        this.id = id;
        this.color = color;
        this.cottonPart = cottonPart;
        this.quantity = quantity;
    }

    public Sock(String color, Byte cottonPart, Short quantity) {
        this.color = color;
        this.cottonPart = cottonPart;
        this.quantity = quantity;
    }

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

    public Byte getCottonPart() {
        return cottonPart;
    }

    public void setCottonPart(Byte cottonPart) {
        this.cottonPart = cottonPart;
    }

    public Short getQuantity() {
        return quantity;
    }

    public void setQuantity(Short quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "Sock{" +
                "id=" + id +
                ", color='" + color + '\'' +
                ", cottonPart=" + cottonPart +
                ", quantity=" + quantity +
                '}';
    }
}
