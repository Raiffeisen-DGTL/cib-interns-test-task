package ru.task.socks.model.entity;

public class SocksEntity {

    private Long id;

    private String color;

    private Float cottonPart;

    private Float quantity;

    public SocksEntity() {
    }

    public SocksEntity(Long id, String color, Float cottonPart, Float quantity) {
        this.id = id;
        this.color = color;
        this.cottonPart = cottonPart;
        this.quantity = quantity;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public Float getCottonPart() {
        return cottonPart;
    }

    public void setCottonPart(Float cottonPart) {
        this.cottonPart = cottonPart;
    }

    public Float getQuantity() {
        return quantity;
    }

    public void setQuantity(Float quantity) {
        this.quantity = quantity;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
