package appAmirSalyakhov.raiffeisenTask.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;
import java.io.Serializable;

@Entity
@Getter
@Setter
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@Valid
@DynamicInsert
@DynamicUpdate
@Table(name = "socks_warehouse")
public class Socks implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    @Column(name = "id")
    private Long id;

    @NotBlank
    @Column(name = "color")
    private String color;

    @Column(name = "cotton_part")
    @Min(0)
    @Max(100)
    private Integer cottonPart;

    @Min(0)
    @Column(name = "available_quantity")
    private int quantity;
}
