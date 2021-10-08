package ru.danilarassokhin.raiffeisensocks.model;

import javax.persistence.*;

@Entity(name = "socks")
@IdClass(SocksId.class)
public class Socks {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Id
    @Column(name = "color")
    private SocksColor socksColor;

    @Id
    @Column(name = "cotton_part")
    private byte cottonPart;

    @Column(name = "quantity")
    private Long quantity;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public SocksColor getSockColor() {
        return socksColor;
    }

    public void setSockColor(SocksColor socksColor) {
        this.socksColor = socksColor;
    }

    public byte getCottonPart() {
        return cottonPart;
    }

    public void setCottonPart(byte cottonPart) {
        this.cottonPart = cottonPart;
    }

    public Long getQuantity() {
        return quantity;
    }

    public void setQuantity(Long quantity) {
        this.quantity = quantity;
    }
}
