package socksRepositoryTestTask.model;

import javax.persistence.*;

@Entity
public class Sock {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Enumerated(EnumType.STRING)
    private Color color;
    private int cottonPart;
    private int quantity;

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Sock() {
    }

    public Sock(Color color, int cottonPart, int quantity) {
        this.color = color;
        this.cottonPart = cottonPart;
        this.quantity = quantity;
    }

    public Long getId() {
        return id;
    }

    public Color getColor() {
        return color;
    }

    public int getCottonPart() {
        return cottonPart;
    }

    public int getQuantity() {
        return quantity;
    }
}
