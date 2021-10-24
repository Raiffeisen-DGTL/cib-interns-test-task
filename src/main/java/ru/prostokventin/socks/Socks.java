package ru.prostokventin.socks;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

@Entity
@Table(name = "socks")
@Data
@NoArgsConstructor
@IdClass(SocksId.class)
public class Socks {

//    @Id
//    @GeneratedValue(strategy = GenerationType.AUTO)
//    @Column(name = "id")
//    private int id;

    @Id
    @Column(name = "color")
    private String color;

    @Id
    @Column(name = "cotton_part")
    @Min(value = 0)
    @Max(value = 100)
    private int cottonPart;

    @Column(name = "quantity")
    @Min(value = 0)
    private int quantity;

    public void increaseQuantity(int income) {
        this.quantity+=income;
    }

    public void decreaseQuantity(int outcome) {
        this.quantity-=outcome;
    }

}
