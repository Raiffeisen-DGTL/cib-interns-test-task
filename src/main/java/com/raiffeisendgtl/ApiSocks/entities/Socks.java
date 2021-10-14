package com.raiffeisendgtl.ApiSocks.entities;

import com.raiffeisendgtl.ApiSocks.components.SocksErrorCode;
import com.raiffeisendgtl.ApiSocks.components.SocksException;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Socks {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String color;

    private int cottonPart;

    private int quantity;

    public Socks() {

    }

    public Socks(String color, int cottonPart, int quantity) {
        setColor(color);
        setCottonPart(cottonPart);
        setQuantity(quantity);
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        if (color == null || color.isEmpty()) {
            throw new SocksException(SocksErrorCode.INCORRECT_PARAMS);
        }
        this.color = color;
    }

    public int getCottonPart() {
        return cottonPart;
    }

    public void setCottonPart(int cottonPart) {
        if (cottonPart < 0 || cottonPart > 100 ) {
            throw new SocksException(SocksErrorCode.INCORRECT_PARAMS);
        }
        this.cottonPart = cottonPart;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        if (quantity <= 0) {
            throw new SocksException(SocksErrorCode.INCORRECT_PARAMS);
        }
        this.quantity = quantity;
    }

    public void addQuantity(int quantity) {
        this.quantity += quantity;
    }

    public void subtractQuantity(int quantity) {
        if (this.quantity < quantity) {
            throw new SocksException(SocksErrorCode.INCORRECT_PARAMS);
        }
        this.quantity -= quantity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Socks socks = (Socks) o;

        if (cottonPart != socks.cottonPart) return false;
        if (quantity != socks.quantity) return false;
        if (id != null ? !id.equals(socks.id) : socks.id != null) return false;
        return color != null ? color.equals(socks.color) : socks.color == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (color != null ? color.hashCode() : 0);
        result = 31 * result + cottonPart;
        result = 31 * result + quantity;
        return result;
    }

}
