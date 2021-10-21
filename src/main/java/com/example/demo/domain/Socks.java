package com.example.demo.domain;

import com.example.demo.dto.SocksDto;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;


@Data
@Table(name = "socks")
@Entity
@NoArgsConstructor
public class Socks {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull()
    private String color;

    @Min(value = 0)
    @Max(100)
    private Long cottonPart;

    @Min(value = 0)
    private Long quantity;

    public Socks(SocksDto socksDto) {
        this.color = socksDto.getColor();
        this.cottonPart = socksDto.getCottonPart();
        this.quantity = socksDto.getQuantity();
    }
}
