package com.raiffeisen.stocktaking.model;


import lombok.*;
import lombok.experimental.Accessors;

import javax.persistence.*;
import javax.validation.constraints.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@With
@Accessors(chain = true)
@Entity(name = "SocksModel")
@Table(name = "socks_request")
public class SocksModel {
    @Id
    @SequenceGenerator(
            name = "socks_sequence",
            sequenceName = "socks_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "socks_sequence"
    )
    private Long id;

    @NotNull
    @NotBlank(message = "color is mandatory")
    private String color;

    @Min(value = 0L, message = "cottonPart should not be less than 0")
    @Max(value = 100L, message = "Age should not be greater than 100")
    private int cottonPart;

    @PositiveOrZero
    private int quantity;
}