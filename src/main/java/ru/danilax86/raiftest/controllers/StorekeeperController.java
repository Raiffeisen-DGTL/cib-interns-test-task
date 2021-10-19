package ru.danilax86.raiftest.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.danilax86.raiftest.models.Socks;

public interface StorekeeperController {
	@PostMapping("/income")
	ResponseEntity<String> addSocks(@RequestBody Socks socks);

	@PostMapping("/outcome")
	ResponseEntity<String> removeSocks(@RequestBody Socks socks);

	@GetMapping
	ResponseEntity<String> getSocks(@RequestParam String color,
	                                @RequestParam String operation,
	                                @RequestParam int cottonPart);
}
