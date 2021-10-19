package com.raiffeisen.interntask.classes;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.UUID;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class SocksOrder {
    @Id
    @GeneratedValue
    private UUID id;

    @ManyToOne
    private Color color;
    private Short cottonPart;
    private Integer quantity;
}
