package com.raiffeisen.test.dtos;

import com.raiffeisen.test.entities.SockBatch;
import lombok.Data;

@Data
public class SockBatchDto {

    private long id;
    private String color;
    private int cottonPart;

    public SockBatchDto(SockBatch sockBatch){
        this.id = sockBatch.getId();
        this.color = sockBatch.getColor();
        this.cottonPart = sockBatch.getCottonPart();
    }
}
