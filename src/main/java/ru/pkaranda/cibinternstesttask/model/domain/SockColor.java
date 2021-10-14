package ru.pkaranda.cibinternstesttask.model.domain;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Data
public class SockColor {

    @Id
    private Long id;
    private String color;

}
