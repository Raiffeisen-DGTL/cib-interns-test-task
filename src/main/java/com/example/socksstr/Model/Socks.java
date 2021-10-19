package com.example.socksstr.Model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.util.List;


@Entity
@Table(name = "Socks")
@Getter
@Setter
@ToString
public class Socks extends BaseEntity {

    @Column (name = "color")
    private String color;

    @Column (name = "cottonPart")
    private Long cottonPart;

    @Column (name = "quantity")
    private BigDecimal quantity;


}
