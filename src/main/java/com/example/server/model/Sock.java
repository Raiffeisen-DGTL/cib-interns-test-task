package com.example.server.model;

import javax.persistence.*;

@Entity
@Table(name = "socks")
public class Sock
{

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int     id;

	@Column(name = "cottonpart")
	private int     cottonPart;

	@Column(name = "color")
	private String  color;

	public int getId()
	{
		return id;
	}

	public void setId(int id)
	{
		this.id = id;
	}

	public int getCottonPart()
	{
		return cottonPart;
	}

	public void setCottonPart(int cottonPart)
	{
		this.cottonPart = cottonPart;
	}

	public String getColor()
	{
		return color;
	}

	public void setColor(String color)
	{
		this.color = color;
	}

	public enum operation
	{
		moreThan, lessThan, equal
	}

	public Sock(Integer cottonPart, String color)
	{
		this.cottonPart = cottonPart;
		this.color = color;
	}

	public Sock() { }


	public boolean cottonPartCompare(Integer cottonPart, operation oper)
	{
		if (oper == operation.moreThan)
		{
			if (this.cottonPart > cottonPart)
				return true;
			return false;
		}
		else if (oper == operation.lessThan)
		{
			if (this.cottonPart < cottonPart)
				return true;
			return false;
		}
		else
		{
			if (this.cottonPart == cottonPart)
				return true;
			return false;
		}
	}
}
