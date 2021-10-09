package ru.strelchm.raiffeisenchallenge.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "socks")
public class Sock extends ParentEntity {

    public Sock(SockColor color, Integer cottonPart) {
        this.color = color;
        this.cottonPart = cottonPart;
        this.counter = 0;
    }

    @Enumerated(EnumType.STRING)
    @NotNull
    private SockColor color;

    @NotNull
    private Integer cottonPart;

    @NotNull
    private Integer counter;
}
