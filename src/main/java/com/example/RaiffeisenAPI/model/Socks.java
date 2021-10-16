package com.example.RaiffeisenAPI.model;

import javax.persistence.*;

@Entity
@Table(name = "Socks")
public class Socks 
{
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

	@Column(name = "Color", nullable = false)
	private String color;

	@Column(name = "Cotton_Part", nullable = false)
	private int cottonPart;
	
	@Column(name = "Quantity", nullable = false)
	private int quantity;
	
	public Socks()
	{
		super();
	}
	
	public Socks(String color, int cottonPart, int quantity)
	{
		this.color = color;
		this.cottonPart = cottonPart;
		this.quantity = quantity;
	}
	
	public String getColor()
	{
		return this.color;
	}
	
	public int getCottonPart()
	{
		return this.cottonPart;
	}
	
	public int getQuantity()
	{
		return this.quantity;
	}
	
	public void setColor(String color)
	{
		this.color = color;
	}
	
	public void setCottonPart(int cottonPart)
	{
		this.cottonPart = cottonPart;
	}
	
	public void setQuantity(int quantity)
	{
		this.quantity = quantity;
	}
}
