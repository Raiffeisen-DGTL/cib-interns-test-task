package org.vetirdoit.sock.registration.domain.entities;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.vetirdoit.sock.registration.domain.Color;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Positive;

@Entity
@Table(name = "sock_types")
@Getter @NoArgsConstructor(access = AccessLevel.PROTECTED)
public class SockType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.PROTECTED)
    private long id;

    @Enumerated(EnumType.STRING)
    private Color color;

    @Column(name = "cotton_part")
    private int cottonPart;

    private int quantity;

    public static SockType createSockType(Color color, int cottonPart, int quantity) {
        SockType sockType = new SockType();
        sockType.setColor(color);
        sockType.setCottonPart(cottonPart);
        sockType.setQuantity(quantity);
        return  sockType;
    }


    public void setColor(Color color) {
        if (color == null)
            throw new IllegalArgumentException("Color must be non-null!");
        this.color = color;
    }

    public void setCottonPart(int cottonPart) {
        if (cottonPart < 0 || cottonPart > 100)
            throw new IllegalArgumentException("Cotton proportion must lie between 0 and 100!");
        this.cottonPart = cottonPart;
    }

    public void setQuantity(int quantity) {
        if (quantity <= 0)
            throw new IllegalArgumentException("Quantity must be non-negative!");
        this.quantity = quantity;
    }

}
