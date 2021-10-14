package ru.raiffeisen.dgtl.cib.intern.task.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

@Data
@Entity
@DynamicUpdate
@AllArgsConstructor
@NoArgsConstructor
@IdClass(SocksId.class)
public class Socks {

    @Id
    private String color;

    @Id
    @Min(value = 0, message = "Cotton part must be more or equal than 0")
    @Max(value = 100, message = "Cotton part must be less than 100")
    private Byte cottonPart;

    @Min(value = 0, message = "Quantity  must be more or equal than 0")
    private Long quantity;
}
