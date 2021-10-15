package com.example.TaskSocks.Model;
import javax.persistence.*;

@Entity
@Table(name = "socks")
public class Socks
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String color;
    private int cotton_part;
    private int quantity;

    public Socks() {
    }

    public Socks(String color, int cotton_part, int quantity) {
        this.color = color;
        this.cotton_part = cotton_part;
        this.quantity = quantity;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getCotton_part() {
        return cotton_part;
    }

    public void setCotton_part(int cotton_part) {
        this.cotton_part = cotton_part;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Long getId() {
        return id;
    }
}
