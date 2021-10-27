package ru.raiffeisen.socksapp.Model;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

@Data
@NoArgsConstructor
@Entity
@IdClass(SocksId.class)
public class Socks {
    @Id
    @NotBlank
    private String color;

    @Id
    @Min(0)
    @Max(100)
    @Column(name = "cottonpart")
    private Integer cottonPart;

    @Min(1)
    private Long quantity;
}
