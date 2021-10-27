package ru.raiffeisen.socksapp.Model;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
public class SocksId implements Serializable {
    private String color;
    private Integer cottonPart;
}
