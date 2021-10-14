package com.warehouse.storewarehouse.counting;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SocksRecords {
    @Id
    @GeneratedValue
    private Long id;
    private String color;
    private int quantity;
    private int cottonPart;

    public SocksRecords(final String color, int quantity, int cottonPart) {
        this(null, color, quantity, cottonPart);
    }

}
