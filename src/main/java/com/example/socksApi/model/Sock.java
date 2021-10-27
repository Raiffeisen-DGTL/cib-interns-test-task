package com.example.socksApi.model;

import com.example.socksApi.dto.IncomeDto;
import com.example.socksApi.dto.OutcomeDto;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@Table(name = "socks")
@AllArgsConstructor
@NoArgsConstructor
public class Sock {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private Integer id;
    private String color;
    private int cottonPart;
    private int amount;

    public Sock(String color, int cottonPart, int amount) {
        this.color = color.toLowerCase();
        this.cottonPart = cottonPart;
        this.amount = amount;
    }

    public Sock(IncomeDto dto) {
        this.color = dto.getColor().toLowerCase();
        this.cottonPart = dto.getCottonPart();
        this.amount = dto.getAmount();
    }

    public Sock(OutcomeDto dto) {
        this.color = dto.getColor().toLowerCase();
        this.cottonPart = dto.getCottonPart();
        this.amount = dto.getAmount();
    }

}
