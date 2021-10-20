package com.raif.socks.entity;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "socks")
@IdClass(SocksEntity.PrimaryKey.class)
public class SocksEntity {
    @Id
    String color;
    @Id
    int cottonPart;
    int quantity;

    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    @EqualsAndHashCode
    public static class PrimaryKey implements Serializable {

        String color;
        int cottonPart;

    }
}
