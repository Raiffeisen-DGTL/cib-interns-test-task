package com.example.RaiffeisenAPI.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.RaiffeisenAPI.model.Socks;
import com.example.RaiffeisenAPI.repository.SocksRepository;


@Service
@Transactional
public class SocksService 
{
	public SocksRepository repository;
	
	@Autowired
    public void setRepository(SocksRepository repository) 
	{
        this.repository = repository;
    }
	
	public SocksService(){   }
	
	public void checkArgs(String color, int cottonPart, int quantity) throws IllegalArgumentException
	{
		ArrayList<String> colorList = new ArrayList<String>(Arrays.asList("black", "white", "red", "orange",
									"yellow", "green", "blue", "purple", "pink", "brown", "grey", "multi"));
		if(!colorList.contains(color.toLowerCase())) throw new IllegalArgumentException("Uncorrect color, you must choose from:" + colorList.toString());
		if(cottonPart < 0 || cottonPart > 100) throw new IllegalArgumentException("Uncorrect cotton part, it must be > 0 and < 100");
		if(quantity <= 0) throw new IllegalArgumentException("Uncorrect quantity, it must be >= 0");
	}
	
	public void income(String color, int cottonPart, int quantity) throws IllegalArgumentException
	{
		checkArgs(color, cottonPart, quantity);
		Socks socks = this.repository.findByColorAndCottonPart(color, cottonPart);
		if(socks != null)
		{
			socks.setQuantity(socks.getQuantity() + quantity);
		}
		else
		{
			socks = new Socks(color, cottonPart, quantity);
		}
		this.repository.saveAndFlush(socks);
	}
	
	public void outcome(String color, int cottonPart, int quantity) throws IllegalArgumentException, NoSuchElementException 
	{
		checkArgs(color, cottonPart, quantity);
		Socks socks = this.repository.findByColorAndCottonPart(color, cottonPart);
		if(socks != null)
		{
			if(socks.getQuantity() - quantity <= 0)
			{
				this.repository.delete(socks);
			}
			else
			{
				socks.setQuantity(socks.getQuantity() - quantity);
				this.repository.saveAndFlush(socks);
			}
		}
		else
		{
			throw new NoSuchElementException("Such socks don`t exist in the database");
		}
	}
	
	public int getSocks(String color, String operation, int cottonPart) throws IllegalArgumentException
	{
		checkArgs(color, cottonPart, 1);
		int num = 0;
		List<Socks> socks = this.repository.findByColor(color);
		switch(operation.toLowerCase())
		{
			case "morethan":
				for (int i = 0; i < socks.size(); i++)
				{
					if (socks.get(i).getCottonPart() > cottonPart) num += socks.get(i).getQuantity();
				}
				break;
			case "lessthan":
				for (int i = 0; i < socks.size(); i++)
				{
					if (socks.get(i).getCottonPart() < cottonPart) num += socks.get(i).getQuantity();
				}
				break;
			case "equals":
				for (int i = 0; i < socks.size(); i++)
				{
					if (socks.get(i).getCottonPart() == cottonPart) num += socks.get(i).getQuantity();
				}
				break;
			default: throw new IllegalArgumentException("Uncorrect operation, you must choose moreThan, lessThan or equals");
		}
		return num;	
	}
}
