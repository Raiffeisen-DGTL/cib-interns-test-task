package ru.pkaranda.cibinternstesttask.model.domain;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

@Data
public class SockColor {

    private Long id;
    private String color;

}
