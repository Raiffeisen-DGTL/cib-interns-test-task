package com.example.testsocks.model;

import lombok.Data;

import java.io.Serializable;

@Data
public class SocksPK implements Serializable {

    protected int cottonPart;
    protected String color;

    public SocksPK() {}

    public SocksPK(String color, int cottonPart) {
        this.cottonPart = cottonPart;
        this.color = color;
    }

    //TODO: переопределить equals и hashcode
}
