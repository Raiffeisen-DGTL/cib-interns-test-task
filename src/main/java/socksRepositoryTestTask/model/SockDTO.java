package socksRepositoryTestTask.model;


import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

public class SockDTO {
    private Color color;

    @Min(0)
    @Max(100)
    private int cottonPart;

    @Min(0)
    private int quantity;

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public SockDTO(Color color, int cottonPart, int quantity) {
        this.color = color;
        this.cottonPart = cottonPart;
        this.quantity = quantity;
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

    public Sock toEntity() {
        return new Sock(color, cottonPart, quantity);
    }
}
