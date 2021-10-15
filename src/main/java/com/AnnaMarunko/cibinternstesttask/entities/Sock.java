package com.AnnaMarunko.cibinternstesttask.entities;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * The type Sock.
 */
@Entity
@Data
@NoArgsConstructor
public class Sock {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long sockId;

    private String color;
    private Integer quantity;
    private Integer cottonPart;

}
