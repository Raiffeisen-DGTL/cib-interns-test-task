package socks;

//Socks packet record class
public class SocksPack {

	private String color;
	private int cottonPart;
	private int quantity;
	
	public SocksPack() {
		
	}
	
	public SocksPack(String color, int cottonPart, int quantity) {
		this.color = color;
		this.cottonPart = cottonPart;
		this.quantity = quantity;
	}
	
	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public int getCottonPart() {
		return cottonPart;
	}

	public void setCottonPart(int cottonPart) {
		this.cottonPart = cottonPart;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
	@Override
	public String toString() {
		return "{\"color\": " + "\"" + color + "\"" + ", \"cottonPart\": " + cottonPart + ", \"quantity\": " + quantity + "}";
	}
	
	public boolean check() {
		return ((this.quantity > 0) && (this.cottonPart >= 0 && this.cottonPart <= 100)) ;
	}
}
