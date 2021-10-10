package ru.strelchm.raiffeisenchallenge.dto;

import lombok.Data;
import ru.strelchm.raiffeisenchallenge.domain.SockColor;

import java.util.Date;
import java.util.UUID;

@Data
public class SockDto {
    private UUID id;
    private Date created;
    private Date updated;
    private SockColor color;
    private Integer cottonPart;
    private Integer counter;
}

