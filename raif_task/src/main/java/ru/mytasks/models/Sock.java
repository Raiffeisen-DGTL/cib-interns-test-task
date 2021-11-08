package ru.mytasks.models;

public class Sock {
	private String color;
	private int cottonPart;
	private int quantity;
	
	public Sock() {
		
	}
	
	public Sock(String color, int cottonPart, int quantity) {
		this.setColor(color);
		this.setCottonPart(cottonPart);
		this.setQuantity(quantity);
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
	
}
