package raif.test.socks.model;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;


@EqualsAndHashCode
public class SockPK implements Serializable {

    @Column(name = "sock_color")
    @NotBlank
    private String color;

    @Column(name = "sock_cotton_part")
    @Min(0)
    @Max(100)
    private Byte cottonPart;

    public SockPK() {}

    public SockPK(String color, Byte cottonPart) { }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public Byte getCottonPart() {
        return cottonPart;
    }

    public void setCottonPart(Byte cottonPart) {
        this.cottonPart = cottonPart;
    }

}
