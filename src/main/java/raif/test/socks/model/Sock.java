package raif.test.socks.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;


@Entity
@Table(name = "socks")
@IdClass(SockPK.class)
public class Sock implements Serializable {

    @Id
    private String color;

    @Id
    private Byte cottonPart;

    @Column(name = "sock_quantity")
    @NotNull
    @Min(1)
    private Integer quantity;

    public Sock() { }

    public Sock(String color, Byte cottonPart, Integer quantity) { }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
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

}
