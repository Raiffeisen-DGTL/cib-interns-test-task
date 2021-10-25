package socks;

import org.json.simple.JSONObject;

public class Socks {
    String color;
    Long cottonPart;
    Long quantity;

    public String getColor() {
        return color;
    }

    public Long getCottonPart() {
        return cottonPart;
    }

    public Long getQuantity() {
        return quantity;
    }

    public Socks(String color, Long cottonPart, Long quantity){
        this.color=color;
        this.cottonPart=cottonPart;
        this.quantity=quantity;
    }
    public Socks(JSONObject jsonObject){
        this.color=(String)jsonObject.get("color");
        this.cottonPart=(Long) jsonObject.get("cottonPart");
        this.quantity=(Long) jsonObject.get("quantity");
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Socks socks = (Socks) o;
        if(color.equals(socks.color))
            if(cottonPart.equals(socks.cottonPart))
                return true;
        return false;
    }

    @Override
    public int hashCode() {
        int result = color != null ? color.hashCode() : 0;
        result = 31 * result + cottonPart.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Socks{" +
                "color='" + color + '\'' +
                ", cottonPart=" + cottonPart +
                ", hash= "+hashCode()+
                '}';
    }
}
