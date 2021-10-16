package ru.raiffeisen.core.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name="socks")
public class SocksInfoModel {

    /**
     * Идентификатор пары.
     */
    @Id
    @Column(name="id")
    private Long id;

    /**
     * Идентификатор цвета.
     */
    @Column(name="color_id")
    private Long colorId;

    /**
     * Процентное содержание хлопка в составе носков.
     */
    @Column(name="cotton_part")
    private int cottonPart;


    /**
     * Количество пар носков.
     */
    @Column(name="quantity")
    private int quantity;


}
