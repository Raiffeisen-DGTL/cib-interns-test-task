package ru.raiffeisen.core.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Table(name="colors")
public class ColorInfoModel {

    @Id
    @Column(name="id")
    private Long id;

    @Column(name="color")
    private String color;
}

