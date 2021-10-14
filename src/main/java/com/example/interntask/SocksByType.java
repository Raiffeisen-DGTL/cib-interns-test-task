package com.example.interntask;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.Table;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Getter
@Setter
@Table(appliesTo = "socks_by_type")
@DynamicUpdate(value = true)
public class SocksByType {
    @Id
    private String type_id;

    @Column
    private String color;

    @Column
    private Integer cottonPart;

    @Column
    private int quantity;

    public SocksByType(String color, Integer cottonPart, Integer quantity){
        this.color = color;
        this.cottonPart = cottonPart;
        this.quantity = quantity;
    }

    public SocksByType() {

    }
}
