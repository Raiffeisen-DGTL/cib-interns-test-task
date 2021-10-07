package raineduc.raiffeiseninternship.entities;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

@Entity
public class SocksPair {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Size(min = 1, max = 100)
    @Column(nullable = false)
    private String color;
    @Min(0)
    @Max(100)
    @Column(name="cotton_part", nullable = false)
    private Byte cottonPart;

    public SocksPair() {};

    public SocksPair(String color, byte cottonPart) {
        this.color = color;
        this.cottonPart = cottonPart;
    }

    public String getColor() {
        return color;
    }

    public byte getCottonPart() {
        return cottonPart;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public void setCottonPart(Byte cottonPart) {
        this.cottonPart = cottonPart;
    }
}
