package com.example.raiftesttask.domain;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.PositiveOrZero;

@Entity
@Table(name = "sock")
@Builder
@Getter
@Setter
@AllArgsConstructor
public class Sock {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Enumerated(EnumType.STRING)
    private Color color;

    @Column(name = "cotton_part")
    @Min(0)
    @Max(100)
    private Integer cottonPart;
    @PositiveOrZero
    private Long quantity;

    public Sock(Color color, Integer cotton, Long quantity){
    this.color=color;
    this.cottonPart=cotton;
    this.quantity=quantity;
}

public void incrementQuantity(Long change){
        this.quantity+=change;
}

public void decrementQuantity(Long change){
        this.quantity-=change;
}
    public Sock() {

    }

    @Override
    public String toString() {
        return "Sock{" +
                "id=" + id +
                ", color=" + color +
                ", cottonPart=" + cottonPart +
                ", quantity=" + quantity +
                '}';
    }
}
