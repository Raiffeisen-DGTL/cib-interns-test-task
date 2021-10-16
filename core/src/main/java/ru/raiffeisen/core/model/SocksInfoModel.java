package ru.raiffeisen.core.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name="socks")
public class SocksInfoModel {

    @Id
    @Column(name="id")
    private Long id;

    @Column(name="color_id")
    private Long colorId;

    @Column(name="cotton_part")
    private int cottonPart;

    @Column(name="quantity")
    private int quantity;


}
