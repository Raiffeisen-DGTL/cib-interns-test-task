package com.example.server.controller;

import com.example.server.model.Sock;
import com.example.server.service.SockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController // Т.е. в данном классе будет реализована логика обработки клиентских запросов
@RequestMapping(value = "/api")
public class SockController
{
	private final SockService sockService;

	@Autowired //
	public SockController(SockService sockService)
	{
		this.sockService = sockService;
	}

	@PostMapping(value = "/socks/income")
	public ResponseEntity<?> create(
			@RequestParam String color,
			@RequestParam int cottonPart,
			@RequestParam int quantity)
	{
		for (int i = 0; i < quantity; i++)
		{
			sockService.create(new Sock(cottonPart, color));
		}
		return  new ResponseEntity<>(HttpStatus.OK);
	}

	@PostMapping(value = "/socks/outcome")
	public ResponseEntity<?> delete(
			@RequestParam String color,
			@RequestParam String cottonPart,
			@RequestParam String quantity)
	{
		int part = Integer.parseInt(cottonPart);
		int quant = Integer.parseInt(quantity);
		ResponseEntity<Integer> numbers = read(color, Sock.operation.equal, part);
		if (numbers.getBody() < quant)
			return new ResponseEntity<Integer>(HttpStatus.BAD_REQUEST);
		List<Sock> socks = sockService.readAll();
		for (Sock sock: socks)
		{
			if (sock.getColor().equals(color) && sock.getCottonPart() == part)
			{
				sockService.delete(sock.getId());
				quant--;
				if (quant == 0)
					break;
			}
		}
		return new ResponseEntity<Integer>(HttpStatus.OK);
	}

	@GetMapping(value = "/socks")
	public ResponseEntity<Integer> read(
			@RequestParam String color,
			@RequestParam Sock.operation operation,
			@RequestParam int cottonPart)
	{
		Integer number = 0;
		List<Sock> socks = sockService.readAll();
		for (Sock sock : socks)
		{
			if (sock.getColor().equals(color) &&
					sock.cottonPartCompare(cottonPart, operation))
			{
				number++;
			}
		}
		if (!socks.isEmpty())
			return new ResponseEntity<Integer>(number, HttpStatus.OK);
		else
			return new ResponseEntity<Integer>(HttpStatus.BAD_REQUEST);
	}








	@GetMapping(value = "/socks/list")
	public ResponseEntity<List<Sock>> read()
	{
		List<Sock> socks = sockService.readAll();
		if (!socks.isEmpty())
			return new ResponseEntity<>(socks, HttpStatus.OK);
		else
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}

	@GetMapping(value = "/socks/just")
	public ResponseEntity<String> read2()
	{
			return new ResponseEntity<>("Hello world!!!", HttpStatus.OK);
	}

}
