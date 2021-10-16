package com.example.RaiffeisenAPI.controller;

import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.bind.annotation.*;

import com.example.RaiffeisenAPI.service.SocksService;


@RestController
@EnableScheduling
public class SocksController 
{
	private SocksService service;
	
	@Autowired
	public SocksController(SocksService service) {
	       this.service = service;
	}
	
	@PostMapping(value = "/api/socks/income")
	public ResponseEntity<String> income(@RequestParam String color, @RequestParam int cottonPart,
            @RequestParam int quantity) 
	{
		try 
		{
			service.income(color, cottonPart, quantity);
			return new ResponseEntity<>("Ok", HttpStatus.CREATED);
		} catch (IllegalArgumentException e) 
		{
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
		catch (Exception e)
		{
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PostMapping(value = "/api/socks/outcome")
	public ResponseEntity<String> outcome(@RequestParam String color, @RequestParam int cottonPart,
            @RequestParam int quantity) 
	{
		try 
		{
			service.outcome(color, cottonPart, quantity);
			return new ResponseEntity<>("Ok", HttpStatus.CREATED);
		} catch (IllegalArgumentException e) 
		{
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
		catch (NoSuchElementException e) 
		{
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
		catch (Exception e)
		{
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping(value = "/api/socks")
	public ResponseEntity<String> getSocks(@RequestParam String color, @RequestParam String operation,
            @RequestParam int cottonPart) 
	{
		try 
		{
			return new ResponseEntity<>(Integer.toString(service.getSocks(color, operation, cottonPart)), HttpStatus.CREATED);
		} catch (IllegalArgumentException e) 
		{
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
		catch (Exception e)
		{
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
