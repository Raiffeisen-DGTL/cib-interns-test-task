package ru.raiffeisen.cibinternstesttask.socks;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Entity(name = "Color")
@Table(name = "colors")
public class Color {

    @Id
    @Column(name = "name", nullable = false, unique = true, length = 32)
    private String name;

    @OneToMany(mappedBy = "color", orphanRemoval = true)
    @ToString.Exclude
    private List<Socks> socks = new ArrayList<>();

    public static Color of(String name) {
        var color = new Color();
        color.setName(name);
        return color;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Color color)) {
            return false;
        }
        return Objects.equals(getName(), color.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName());
    }
}
