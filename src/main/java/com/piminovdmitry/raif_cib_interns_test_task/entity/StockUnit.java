package com.piminovdmitry.raif_cib_interns_test_task.entity;

import javax.persistence.*;


@Entity
@Table(name="stock_units")
public class StockUnit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int id;

    @Column(name="color")
    private String color;

    @Column(name="cotton_part")
    private int cottonPart;

    @Column(name="quantity")
    private int quantity;

    public StockUnit() {
    }

    public StockUnit(int id, String color, int cottonPart, int quantity) {
        this.id = id;
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

    @Override
    public String toString() {
        return "StockUnitMove{" +
                "id=" + id +
                ", color='" + color + '\'' +
                ", cottonPart='" + cottonPart + '\'' +
                ", quantity=" + quantity +
                '}';
    }

    public boolean IsInvalid() {
        return (cottonPart < 0 || cottonPart > 100 || quantity <= 0 || color == null || color.equals(""));
    }
}
