package ru.danilarassokhin.raiffeisensocks.model;

import java.io.Serializable;

public class SocksId implements Serializable {

    private Long id;

    private SocksColor socksColor;

    private byte cottonPart;

    public SocksColor getSocksColor() {
        return socksColor;
    }

    public void setSocksColor(SocksColor socksColor) {
        this.socksColor = socksColor;
    }

    public byte getCottonPart() {
        return cottonPart;
    }

    public void setCottonPart(byte cottonPart) {
        this.cottonPart = cottonPart;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public int hashCode() {
        return id.intValue();
    }
}
