package com.oleg.socks.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@SequenceGenerator(allocationSize = 1, name = "socks_seq", sequenceName = "socks_seq")
@Table(name = "socks", uniqueConstraints = { @UniqueConstraint(columnNames = { "color", "cotton_part" }) })
public class SocksEntity {

    @Id
    @GeneratedValue(generator = "socks_seq")
    @Column(name = "socks_id")
    private Long socksId;

    @Column(name = "color")
    private String color;

    @Column(name = "cotton_part")
    private Integer cottonPart;

    @Column(name = "quantity")
    private Long quantity;

}
