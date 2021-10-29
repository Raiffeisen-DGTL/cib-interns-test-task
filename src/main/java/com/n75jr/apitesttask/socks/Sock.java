package com.n75jr.apitesttask.socks;

import javax.persistence.*;
import java.math.BigInteger;
import java.time.LocalDate;

@Entity
@Table(name = "socks")
public class Sock {
    @Id
    private long id;

    @Column(name = "color")
    private String color;

    @Column(name = "cotton_part")
    private int cottonPart;

    @Column(name = "timestamp")
    private LocalDate date;

    public Sock() {}

    public Sock(String color, int cottonPart) {
        this.color = color;
        this.cottonPart = cottonPart;
    }

    public long getId() {
        return id;
    }

    public String getColor() {
        return color;
    }

    public int getCottonPart() {
        return cottonPart;
    }

    public LocalDate getDate() {
        return date;
    }

//    public void setId(long id) {
//        this.id = id;
//    }

    public void setColor(String color) {
        this.color = color;
    }

    public void setCottonPart(int cottonPart) {
        this.cottonPart = cottonPart;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Sock{" +
                "id=" + id +
                ", color='" + color + '\'' +
                ", cottonPart=" + cottonPart +
                ", date=" + date +
                '}';
    }
}
