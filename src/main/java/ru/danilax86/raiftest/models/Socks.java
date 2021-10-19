package ru.danilax86.raiftest.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "socks")
public class Socks {
	@Id
	@GeneratedValue
	private int id;
	private String color;
	private int cottonPart;
	private int quantity;
}
