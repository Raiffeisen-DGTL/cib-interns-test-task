package com.raiffeisendgtl.ApiSocks.entities;

import com.raiffeisendgtl.ApiSocks.components.exception.SocksErrorCode;
import com.raiffeisendgtl.ApiSocks.components.exception.SocksException;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.*;

@Entity
@EqualsAndHashCode
@Getter @Setter @NoArgsConstructor
public class Socks {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotEmpty
    private String color;

    @Min(0)
    @Max(100)
    private int cottonPart;

    @Positive
    private int quantity;

    public Socks(String color, int cottonPart, int quantity) {
        this.color = color;
        this.cottonPart = cottonPart;
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

}
