package ru.simbial.cibinternstesttask.app.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Objects;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "socks")
public class SocksDBModel implements Serializable {

    @EmbeddedId
    private SocksId id;

    @Column(name = "quantity")
    private Long quantity;

    @NoArgsConstructor
    @AllArgsConstructor
    @Data
    @Embeddable
    public static class SocksId implements Serializable {
        private static final long serialVersionUID = 1L;

        @NotBlank
        @Column(name = "color")
        private String color;

        @NotBlank
        @Size(max = 100, message = "Value must be >=0 and <=100")
        @Column(name = "cottonPart")
        private Integer cottonPart;

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof SocksId)) return false;
            SocksId socksId = (SocksId) o;
            return Objects.equals(getColor(), socksId.getColor()) &&
                    Objects.equals(getCottonPart(), socksId.getCottonPart());
        }

        @Override
        public int hashCode() {
            return Objects.hash(getColor(), getCottonPart());
        }
    }
}
