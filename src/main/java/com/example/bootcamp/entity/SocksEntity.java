package com.example.bootcamp.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Positive;
import java.io.Serializable;

@Entity
@Table(name = "Socks_Entity")
@Getter
@Setter
@NoArgsConstructor
public class SocksEntity implements Serializable {

    @Id
    @GeneratedValue
    private Long id;

    @NotEmpty
    private String colorEntity;

    private short cottonPartEntity;

    private int quantityEntity;

    public SocksEntity(String colorEntity, short cottonPartEntity, int quantityEntity) {
        this.colorEntity = colorEntity;
        this.cottonPartEntity = cottonPartEntity;
        this.quantityEntity = quantityEntity;
    }
}
