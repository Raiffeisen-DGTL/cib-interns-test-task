package ru.mytasks.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import ru.mytasks.models.Sock;
import ru.mytasks.services.SockService;

@RestController
//@Controller
@RequestMapping("/api/socks")
//@RequestMapping("/")
//@ResponseBody
public class SocksController {
	private SockService sockService;
	
	@Autowired
	public SocksController(SockService sockService) {
		this.sockService = sockService;
	}

	@PostMapping("income")
	public ResponseEntity<Sock> income(@RequestBody Sock sock) {
		return ResponseEntity.ok().body(sockService.income(sock));
	}
	
	@PostMapping("outcome")
	public ResponseEntity<Sock> outcome(@RequestBody Sock sock) {
		return ResponseEntity.ok().body(sockService.outcome(sock));
	}
	
	@GetMapping("")
	public ResponseEntity<Integer> getSumSocks(@PathVariable("color") String color
			               ,@PathVariable("operation") String operation
			               ,@PathVariable("cottonPart") int CottonPart) {
		return ResponseEntity.ok().body(sockService.getQuantitySocks(color, operation, CottonPart));
	}
	
	@GetMapping("/test_post")
	public String test_post () {
		return "test_post";
	}
}
