package ru.prostokventin.socks;

import lombok.*;

import javax.persistence.Column;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SocksId implements Serializable {

    private String color;

    private int cottonPart;

}
